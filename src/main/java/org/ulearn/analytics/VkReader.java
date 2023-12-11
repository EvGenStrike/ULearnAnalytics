package org.ulearn.analytics;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class VkReader {
    private final Data data;
    private final VkRepository vk;
    public VkReader(Data data){
        this.data = data;
        this.vk = new VkRepository();
    }

    public Data getData(){
        return this.data;
    }

    public void addCitiesToStudents(){
        try{
            var groups = vk.getSubscribedStudentsWithCity();
            var students = data.getStudents();
            for (var student : students){
                var optionalCity = groups.stream()
                        .filter(x -> x.getFirstName().equals(student.getName())
                                    && x.getLastName().equals(student.getSurname()))
                        .findFirst();
                if (optionalCity.isEmpty()){
                    continue;
                }
                var city = optionalCity.get().getCity();
                System.out.println(city);
                if (city == null){
                    continue;
                }
                var cityString = city.getTitle();
                student.setCity(cityString);
            }
        }
        catch (ClientException | ApiException e){
            System.out.println("Failed");
            e.printStackTrace();
        }
    }
}
