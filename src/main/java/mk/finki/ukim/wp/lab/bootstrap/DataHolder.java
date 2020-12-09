package mk.finki.ukim.wp.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Balloon> balloonList = new ArrayList<>(10);
    public static List<Order> orderList = new ArrayList<>();
    public static List<Manufacturer> manufacturerList = new ArrayList<>();
    @PostConstruct
    public void init(){
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer1","MKD","adresa1");
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer2","MKD","adresa2");
        Manufacturer manufacturer3 = new Manufacturer("Manufacturer3","MKD","adresa3");
        Manufacturer manufacturer4 = new Manufacturer("Manufacturer4","MKD","adresa4");
        Manufacturer manufacturer5 = new Manufacturer("Manufacturer5","MKD","adresa4");
        balloonList.add(new Balloon("Red","Red Balloon",manufacturer1, Type.A));
        balloonList.add(new Balloon("Green","Green Balloon",manufacturer2, Type.B));
        balloonList.add(new Balloon("Blue","Blue Balloon",manufacturer3, Type.C));
        balloonList.add(new Balloon("Yellow","Yellow Balloon",manufacturer4, Type.D));
        balloonList.add(new Balloon("Cyan","Cyan Balloon",manufacturer5, Type.A));
        balloonList.add(new Balloon("Purple","Purple Balloon",manufacturer1, Type.B));
        balloonList.add(new Balloon("Orange","Orange Balloon",manufacturer2, Type.C));
        balloonList.add(new Balloon("Black","Black Balloon",manufacturer3, Type.D));
        balloonList.add(new Balloon("White","White Balloon",manufacturer4, Type.A));
        balloonList.add(new Balloon("Gray","Gray Balloon",manufacturer5, Type.B));
        manufacturerList.add(manufacturer1);
        manufacturerList.add(manufacturer2);
        manufacturerList.add(manufacturer3);
        manufacturerList.add(manufacturer4);
        manufacturerList.add(manufacturer5);
    }
}
