#!/usr/bin/env bash
java -p mods/com.baeldung.library.test:\
mods/com.baeldung.library.core:\
/Users/yauhenikauko/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.9.2/junit-jupiter-engine-5.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/platform/junit-platform-console/1.9.2/junit-platform-console-1.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/platform/junit-platform-reporting/1.9.2/junit-platform-reporting-1.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/platform/junit-platform-launcher/1.9.2/junit-platform-launcher-1.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/platform/junit-platform-engine/1.9.2/junit-platform-engine-1.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.9.2/junit-jupiter-params-5.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.9.2/junit-jupiter-api-5.9.2.jar:\
/Users/yauhenikauko/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:\
/Users/yauhenikauko/.m2/repository/org/junit/platform/junit-platform-commons/1.9.2/junit-platform-commons-1.9.2.jar \
--add-modules com.baeldung.library.test \
--add-opens com.baeldung.library.core/com.baeldung.library.core=com.baeldung.library.test \
org.junit.platform.console.ConsoleLauncher -c com.baeldung.library.test.LibraryUnitTest