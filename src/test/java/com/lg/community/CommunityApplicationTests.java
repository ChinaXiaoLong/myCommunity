package com.lg.community;

import com.alibaba.fastjson.JSON;
import com.lg.community.entity.GitHubUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void testJson(){
        String str ="{\"login\":\"ChinaXiaoLong\",\"id\":34051133,\"node_id\":\"MDQ6VXNlcjM0MDUxMTMz\",\"avatar_url\":\"https://avatars1.githubusercontent.com/u/34051133?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/ChinaXiaoLong\",\"html_url\":\"https://github.com/ChinaXiaoLong\",\"followers_url\":\"https://api.github.com/users/ChinaXiaoLong/followers\",\"following_url\":\"https://api.github.com/users/ChinaXiaoLong/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/ChinaXiaoLong/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/ChinaXiaoLong/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/ChinaXiaoLong/subscriptions\",\"organizations_url\":\"https://api.github.com/users/ChinaXiaoLong/orgs\",\"repos_url\":\"https://api.github.com/users/ChinaXiaoLong/repos\",\"events_url\":\"https://api.github.com/users/ChinaXiaoLong/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/ChinaXiaoLong/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":null,\"company\":null,\"blog\":\"\",\"location\":null,\"email\":null,\"hireable\":null,\"bio\":null,\"public_repos\":3,\"public_gists\":0,\"followers\":0,\"following\":0,\"created_at\":\"2017-11-28T04:35:08Z\",\"updated_at\":\"2019-07-25T02:02:16Z\",\"private_gists\":0,\"total_private_repos\":0,\"owned_private_repos\":0,\"disk_usage\":378,\"collaborators\":0,\"two_factor_authentication\":false,\"plan\":{\"name\":\"free\",\"space\":976562499,\"collaborators\":0,\"private_repos\":10000}}";
        GitHubUser gitHubUser = JSON.parseObject(str, GitHubUser.class);
        System.out.println(gitHubUser);
    }

}
