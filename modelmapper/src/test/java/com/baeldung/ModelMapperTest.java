package com.baeldung;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bealdung.domain.Game;
import com.bealdung.domain.GameMode;
import com.bealdung.domain.GameSettings;
import com.bealdung.domain.Player;
import com.bealdung.dto.GameDTO;
import com.bealdung.dto.PlayerDTO;
import com.bealdung.repository.GameRepository;
import java.time.Instant;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperTest {

  ModelMapper mapper;
  GameRepository gameRepository;

  @BeforeEach
  public void setup(){
    this.mapper = new ModelMapper();
    this.gameRepository = new GameRepository();
  }

  @Test
  public void whenMapGameWithExactMatch_convertsToDTO(){
    // when similar source object is provided
    final Game game = new Game(1L, "Game 1");
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then it maps without property mapper
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
  }

  @Test
  public void whenMapGameWithBasicPropertyMapping_convertsToDTO(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    propertyMapper.addMapping(Game::getTimestamp, GameDTO::setCreationTime);
    // when field names are different
    final Game game = new Game(1L, "Game 1");
    game.setTimestamp(Instant.now().getEpochSecond());
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then it maps via property mapper
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals(game.getTimestamp(), gameDTO.getCreationTime());
  }

  @Test
  public void whenMapGameWithDeepMapping_convertsToDTO(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    // add deep mapping to flatten source's Player into name in destination
    propertyMapper.addMappings(
        mapper -> mapper.map(src -> src.getCreator().getName(), GameDTO::setCreator)
    );
    // when map between different hierarchies
    final Game game = new Game(1L, "Game 1");
    game.setCreator(new Player(1L, "John"));
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals(game.getCreator().getName(), gameDTO.getCreator());
  }

  @Test
  public void whenMapGameWithDifferentTypedProperties_convertsToDTO(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    propertyMapper.addMappings(mapper -> mapper.map(src -> src.getCreator().getId(), GameDTO::setCreatorId));
    // when map different typed properties
    final Game game = new Game(1L, "Game 1");
    game.setCreator(new Player(1L, "John"));
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then it converts between types
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals("1", gameDTO.getCreatorId());
  }

  @Test
  public void whenMapGameWithSkipIdProperty_convertsToDTO(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    propertyMapper.addMappings(mapper -> mapper.skip(GameDTO::setId));
    // when id is skipped
    final Game game = new Game(1L, "Game 1");
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then destination id is null
    assertNull(gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
  }

  @Test
  public void whenMapGameWithCustomConverter_convertsToDTO(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    final Converter<Collection, Integer> collectionToSize = c -> c.getSource().size();
    propertyMapper.addMappings(
        mapper -> mapper.using(collectionToSize).map(Game::getPlayers, GameDTO::setTotalPlayers)
    );
    // when collection to size converter is provided
    final Game game = new Game(1L, "Game 1");
    game.setCreator(new Player(1L, "John"));
    game.addPlayer(new Player(2L, "Bob"));
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then it maps the size to a custom field
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals(game.getPlayers().size(), gameDTO.getTotalPlayers());
  }

  @Test
  public void whenUsingProvider_mergesGameInstances(){
    // setup
    final TypeMap<Game, Game> propertyMapper = this.mapper.createTypeMap(Game.class, Game.class);
    // a provider to fetch a Game instance from a repository
    final Provider<Game> gameProvider = p -> this.gameRepository.findById(1L);
    propertyMapper.setProvider(gameProvider);
    // when a state for update is given
    final Game update = new Game(1L, "Game Updated!");
    update.setCreator(new Player(1L, "John"));
    final Game updatedGame = this.mapper.map(update, Game.class);
    // then it merges the updates over on the provided instance
    assertEquals(1L, updatedGame.getId().longValue());
    assertEquals("Game Updated!", updatedGame.getName());
    assertEquals("John", updatedGame.getCreator().getName());
  }

  @Test
  public void whenUsingConditionalIsNull_mergesGameInstancesWithoutOverridingId(){
    // setup
    final TypeMap<Game, Game> propertyMapper = this.mapper.createTypeMap(Game.class, Game.class);
    propertyMapper.setProvider(p -> this.gameRepository.findById(2L));
    propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Game::getId, Game::setId));
    // when game has no id
    final Game update = new Game(null, "Not Persisted Game!");
    final Game updatedGame = this.mapper.map(update, Game.class);
    // then destination game id is not overwritten
    assertEquals(2L, updatedGame.getId().longValue());
    assertEquals("Not Persisted Game!", updatedGame.getName());
  }

  @Test
  public void whenUsingCustomConditional_convertsDTOSkipsZeroTimestamp(){
    // setup
    final TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
    final Condition<Long, Long> hasTimestamp = ctx -> ctx.getSource() != null && ctx.getSource() > 0;
    propertyMapper.addMappings(mapper -> mapper.when(hasTimestamp).map(Game::getTimestamp, GameDTO::setCreationTime));
    // when game has zero timestamp
    final Game game = new Game(1L, "Game 1");
    game.setTimestamp(0L);
    GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then timestamp field is not mapped
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertNotEquals(0L ,gameDTO.getCreationTime());
    assertNull(gameDTO.getCreationTime());
    // when game has timestamp greater than zero
    game.setTimestamp(Instant.now().getEpochSecond());
    gameDTO = this.mapper.map(game, GameDTO.class);
    // then timestamp field is mapped
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals(game.getTimestamp() ,gameDTO.getCreationTime());
    assertNotNull(gameDTO.getCreationTime());
  }

  @Test
  public void whenUsingLooseMappingStrategy_convertsToDomain(){
    // setup
    this.mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.LOOSE);
    // when dto has flat fields for GameSetting
    GameDTO gameDTO = new GameDTO();
    gameDTO.setId(1L);
    gameDTO.setName("Game 1");
    gameDTO.setMode(GameMode.TURBO);
    gameDTO.setMaxPlayers(8);
    Game game = this.mapper.map(gameDTO, Game.class);
    // then it converts to inner objects without property mapper
    assertEquals(gameDTO.getId(), game.getId());
    assertEquals(gameDTO.getName(), game.getName());
    assertEquals(gameDTO.getMode(), game.getSettings().getMode());
    assertEquals(gameDTO.getMaxPlayers(), game.getSettings().getMaxPlayers());
    // when the GameSetting's field names match
    game = new Game(1L, "Game 1");
    game.setSettings(new GameSettings(GameMode.NORMAL, 6));
    gameDTO = this.mapper.map(game, GameDTO.class);
    // then it flattens the fields on dto
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    assertEquals(game.getSettings().getMode(), gameDTO.getMode());
    assertEquals(game.getSettings().getMaxPlayers(), gameDTO.getMaxPlayers());
  }

  @Test
  public void whenConfigurationSkipNullEnabled_convertsToDTO(){
    // setup
    this.mapper.getConfiguration().setSkipNullEnabled(true);
    final TypeMap<Game, Game> propertyMap = this.mapper.createTypeMap(Game.class, Game.class);
    propertyMap.setProvider(p -> this.gameRepository.findById(2L));
    // when game has no id
    final Game update = new Game(null, "Not Persisted Game!");
    final Game updatedGame = this.mapper.map(update, Game.class);
    // then destination game id is not overwritten
    assertEquals(2L, updatedGame.getId().longValue());
    assertEquals("Not Persisted Game!", updatedGame.getName());
  }

  @Test
  public void whenConfigurationPreferNestedPropertiesDisabled_convertsCircularReferencedToDTO(){
    // setup
    this.mapper.getConfiguration().setPreferNestedProperties(false);
    // when game has circular reference
    final Game game = new Game(1L, "Game 1");
    final Player player = new Player(1L, "John");
    player.setCurrentGame(game);
    game.addPlayer(player);
    final GameDTO gameDTO = this.mapper.map(game, GameDTO.class);
    // then it resolves without any exception
    assertEquals(game.getId(), gameDTO.getId());
    assertEquals(game.getName(), gameDTO.getName());
    final PlayerDTO playerDTO = gameDTO.getPlayers().get(0);
    assertEquals(player.getId(), playerDTO.getId());
    assertEquals(player.getCurrentGame().getId(), playerDTO.getCurrentGame().getId());
  }

}
