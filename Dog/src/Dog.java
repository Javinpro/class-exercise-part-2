public abstract class Dog {
    private String size;
    private String name; 
    private String type_dog;
    private int jadwalMakan;
    private boolean sudahMakan;

    public Dog(){

    }

    public Dog(String size, String name, String type_dog, boolean sudahMakan){
        int jadwalMakan;
        switch (size) {
            case "small":
                jadwalMakan = 1;
                break;
            case "medium":
                jadwalMakan = 2;
                break;
            case "large":
                jadwalMakan = 3;
                break;
            default:
                jadwalMakan = 0;
        }
        this.size = size;
        this.name = name;
        this.type_dog = type_dog;
        this.jadwalMakan = jadwalMakan;
        this.sudahMakan = sudahMakan;
    }

    public String doneEat(){
        String teks;
        if(sudahMakan){
            teks = "sleep and get in cage";
        } else {
            teks = "barking...";
        }
        return teks;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_dog() {
        return this.type_dog;
    }

    public void setType_dog(String type_dog) {
        this.type_dog = type_dog;
    }

    public int getJadwalMakan() {
        return this.jadwalMakan;
    }

    public void setJadwalMakan(int jadwalMakan) {
        this.jadwalMakan = jadwalMakan;
    }

    public boolean isSudahMakan() {
        return this.sudahMakan;
    }

    public boolean getSudahMakan() {
        return this.sudahMakan;
    }

    public void setSudahMakan(boolean sudahMakan) {
        this.sudahMakan = sudahMakan;
    }    
}


