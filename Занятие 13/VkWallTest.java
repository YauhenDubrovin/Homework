package vk;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class VkWallTest {
    private static final String ACCESS_TOKEN =
            "e71e718621b73dc453901ed17a659233c918e9523ebeb4a84d88716c2436c400aa540920fe96a8c5c3a30";
    private static final String VERSION = "5.103";
    private static final String OWNER_ID = "26219477";
    private static final String MESSAGE = "test message";
    private static final String CHANGED_MESSAGE = "changed text message";
    private String postId;
    HttpClient client = HttpClientBuilder.create().build();

    public boolean adPost() throws IOException, URISyntaxException {
        URIBuilder builderPost = new URIBuilder("https://api.vk.com/method/wall.post?");
        builderPost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("message", MESSAGE)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderPost.build());
        HttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity())
                .replaceFirst("\\{\"response\":", "")
                .replace("}}", "}");
        JSONObject jsonObject = new JSONObject(result);
        postId = Integer.toString(jsonObject.getInt("post_id"));
        System.out.println("Post added");
        return jsonObject.has("post_id");
    }

    public boolean compareAddedPost() throws URISyntaxException, IOException {
        URIBuilder builderGetPost = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builderGetPost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postId)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderGetPost.build());
        HttpResponse response = client.execute(request);
        String addedPost = EntityUtils.toString(response.getEntity())
                .replaceFirst("\\{\"response\":\\[", "")
                .replace("}]", "");
        JSONObject jsonObject = new JSONObject(addedPost);
        System.out.println("Post contains expected message");
        return jsonObject.getString("text").equals(MESSAGE);
    }

    public boolean changePost() throws URISyntaxException, IOException {
        URIBuilder builderChangePost = new URIBuilder("https://api.vk.com/method/wall.edit?");
        builderChangePost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postId)
                .setParameter("message", CHANGED_MESSAGE)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderChangePost.build());
        HttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity())
                .replaceFirst("\\{\"response\":", "")
                .replace("}}", "}");
        JSONObject jsonObject = new JSONObject(result);
        System.out.println("Post changed");
        return jsonObject.has("post_id");
    }

    public boolean compareChangedPost() throws URISyntaxException, IOException {
        URIBuilder builderGetChangedPost = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builderGetChangedPost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postId)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderGetChangedPost.build());
        HttpResponse response = client.execute(request);
        String changedPost = EntityUtils.toString(response.getEntity())
                .replaceFirst("\\{\"response\":\\[", "")
                .replace("}]", "");
        JSONObject jsonObject = new JSONObject(changedPost);
        System.out.println("Post contains changed message");
        return jsonObject.getString("text").equals(CHANGED_MESSAGE);
    }

    public boolean deletePost() throws URISyntaxException, IOException {
        URIBuilder builderDeletePost = new URIBuilder("https://api.vk.com/method/wall.delete?");
        builderDeletePost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postId)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderDeletePost.build());
        HttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("Post deleted");
        return result.equals("{\"response\":1}");
    }

    public boolean checkDeletedPost() throws URISyntaxException, IOException {
        URIBuilder builderCheckDeletedPost = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builderCheckDeletedPost.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postId)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builderCheckDeletedPost.build());
        HttpResponse response = client.execute(request);
        String noPostMessage = EntityUtils.toString(response.getEntity());
        System.out.println("There is no post on the wall");
        return noPostMessage.equals("{\"response\":[]}");
    }

    @Test
    public void testVkWall() throws IOException, URISyntaxException {
        Assert.assertTrue(adPost());
        Assert.assertTrue(compareAddedPost());
        Assert.assertTrue(changePost());
        Assert.assertTrue(compareChangedPost());
        Assert.assertTrue(deletePost());
        Assert.assertTrue(checkDeletedPost());
    }
}
