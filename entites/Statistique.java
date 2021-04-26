package entites;

import Services.PromotionService;
import Services.ReclamationService;

import java.util.HashMap;
import java.util.List;

public class Statistique {


    public static HashMap<String , Integer> getStatTypeReclamation()
    {
        ReclamationService reclamationService = new ReclamationService();
        HashMap<String , Integer> map = new HashMap<>();
        List<Reclamation> list= reclamationService.getAll();
        map.put("Insatisfaction_liée_au_lieu",0);
        map.put("Mauvaise_organisation",0);
        map.put("horaires_de_début_non_respecté",0);
        map.put("other",0);

        for (Reclamation reclamation : list)
        {
            if (reclamation.getType().contains("Insatisfaction"))
                map.put("Insatisfaction_liée_au_lieu",map.get("Insatisfaction_liée_au_lieu")+1);
            else if (reclamation.getType().contains("Mauvaise"))
                map.put("Mauvaise_organisation",map.get("Mauvaise_organisation")+1);
            else if (reclamation.getType().contains("horaires"))
                map.put("horaires_de_début_non_respecté",map.get("horaires_de_début_non_respecté")+1);
            else
                map.put("other",map.get("other")+1);
        }

        return map;
    }


    public static int[] getStatDateReclamation()
    {
        ReclamationService reclamationService = new ReclamationService();
        int[] tab = {0,0,0,0,0,0,0,0,0,0,0,0} ;
        List<Reclamation> list= reclamationService.getAll();
        for (Reclamation reclamation : list)
        {
            String date = reclamation.getDate_reclamation().toString().split("-")[1];
            int month =Integer.parseInt(date);
            if (month>=10)
                tab[month-1]=tab[month-1]+1;
            else
                tab[Integer.parseInt(String.valueOf(date.charAt(1)))-1]=tab[Integer.parseInt(String.valueOf(date.charAt(1)))-1]+1;
        }

        return tab;
    }




    public static int[] getStatDatePromotion()
    {
        PromotionService promotionService = new PromotionService();
        int[] tab = {0,0,0,0,0,0,0,0,0,0,0,0} ;
        List<Promotion> list= promotionService.getAll();
        for (Promotion promotion : list)
        {
            String date_debut = promotion.getDate_debut().toString().split("-")[1];
            int month_debut =Integer.parseInt(date_debut);
            if (month_debut>=10)
                tab[month_debut-1]=tab[month_debut-1]+1;
            else
                tab[Integer.parseInt(String.valueOf(date_debut.charAt(1)))-1]=tab[Integer.parseInt(String.valueOf(date_debut.charAt(1)))-1]+1;


        }

        return tab;
    }

}
