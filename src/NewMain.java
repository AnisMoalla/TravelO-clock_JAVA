
import java.sql.Date;
import oussema.Entities.Guide;
import oussema.Entities.ReservationGuide;
import oussema.Services.GuideCRUD;
import oussema.Services.ReservationGuideCRUD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masso
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        GuideCRUD gc = new GuideCRUD();
//            Guide g = new Guide(1, "italy", "rome","60670b55a0ebb.jpeg" , 23232323, 23, "male", "he","he","italien",12, 1.9);
//            gc.ajouterGuide(g);
    String date="2020-12-04" ;
    System.out.println(Date.valueOf(date));
        System.out.println(Date.valueOf(date));
    ReservationGuide rg = new ReservationGuide(1, 5, Date.valueOf(date));
    ReservationGuideCRUD rgc= new ReservationGuideCRUD();
    rgc.ajouterReservationGuide(rg);
    }
    
}
