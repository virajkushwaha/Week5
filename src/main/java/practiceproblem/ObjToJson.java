package practiceproblem;

import org.json.JSONObject;

class Car{
    String model;
    String name;
    String type;
    Car(String model,
    String name,
    String type
    ){
        this.name = name;
        this.model = model;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
public class ObjToJson {
    public static void main(String[] args) {
        JSONObject json1 = new JSONObject();
        Car car = new Car("dw46sd54","BMW","Diesel");
        json1.put("Model",car.getModel());
        json1.put("Name",car.getName());
        json1.put("Type",car.getType());
        System.out.println(json1.toString(4));
    }
}
