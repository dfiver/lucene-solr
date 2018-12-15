import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Car {

    //号牌生成规则
    private static String provinces = "京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川黔云渝藏陕甘青宁新港澳台";
    private static String citys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String others = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";

    public Car(String plate, int carType, int carBand) {
        this.plate = plate;
        this.carType = carType;
        this.carBand = carBand;
    }

    public String getPlate() {
        return plate;
    }

    public int getCarType() {
        return carType;
    }

    public int getCarBand() {
        return carBand;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public void setCarBand(int carBand) {
        this.carBand = carBand;
    }

    private String plate;
    private int carType;
    private int carBand;

    @Override
    public String toString() {
        return "Car{" +
                "plate='" + plate + '\'' +
                ", carType=" + carType +
                ", carBand=" + carBand +
                '}';
    }

    public static void main(String[] argv){
            Set plateSet = new HashSet<String>();
            int i=0;
            while(i<1000000){
                Random random = new Random();
                char province = provinces.charAt(random.nextInt(34));
                char city = citys.charAt(random.nextInt(26));
                StringBuffer sb = new StringBuffer();
                sb.append(province).append(city);
                for(int j=0; j<5; ++j) {
                    sb.append(others.charAt(random.nextInt(36)));
                }
                String plate = sb.toString();
                if(plateSet.add(plate)){
                    ++i;
                    System.out.println(i+":"+plate);
                }
            }
    }
}

