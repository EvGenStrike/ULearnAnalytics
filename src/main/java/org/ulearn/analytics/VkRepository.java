package org.ulearn.analytics;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.UserXtrRole;
import com.vk.api.sdk.objects.users.Fields;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VkRepository {
    private final long APP_ID;
    private final String CODE;
    private final VkApiClient vk;
    private final UserActor actor;

    private final String URFU_CLUB_ID = "215279260";

    public VkRepository(){
        Map<String, String> params = getConfigParams();
        APP_ID = Integer.parseInt(params.get("APP_ID"));
        CODE = params.get("CODE");

        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public List<UserXtrRole> getSubscribedStudentsWithCity() throws ClientException, ApiException {
        var result = vk.groups()
                .getMembersWithFields(actor, Fields.CITY)
                .groupId(URFU_CLUB_ID)
                .execute()
                .getItems();

        return result;
    }

    private Map<String, String> getConfigParams(){
        Map<String, String> params = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("config.txt"));
            for (String line : lines){
                String[] splittedLine = line.split("=");
                params.put(splittedLine[0], splittedLine[1]);
                System.out.printf("%s=%s%n", splittedLine[0], splittedLine[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error reading config file");
        }

        return params;
    }
}
