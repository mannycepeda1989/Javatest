package com.example.retrofitrx;

import java.util.List;

import com.example.models.Contributor;
import com.example.models.Repository;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubRxApi {

    /**
     * List GitHub repositories of user
     * @param user GitHub Account
     * @return GitHub repositories
     */
    @GET("users/{user}/repos")
    Observable<List<Repository>> listRepos(@Path("user") String user);
    
    @GET("repos/{user}/{repo}/contributors")
    Observable<List<Contributor>> listRepoContributors(
      @Path("user") String user,
      @Path("repo") String repo);
    
}
