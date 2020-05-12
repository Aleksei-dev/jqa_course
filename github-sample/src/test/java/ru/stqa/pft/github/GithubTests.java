package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("96e2f90938f26b56381fcb32d6dfceb6b7c04d41");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Aleksei-dev", "jqa_course")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
