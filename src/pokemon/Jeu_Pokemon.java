/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jeu_Pokemon extends Application
{
  
        
        
        
        
    public static void main(String[] args)
    {
        
        
        launch(args);
    }
    

    @Override
    public void start(Stage primaryStage)
    {
        
        
        
        
        
        // Creation des variables
        
        //Types
        

        
        
        // Types d'exemple (ajuster selon la classe Type existante)
        Type feu = new Type("Feu");
        Type eau = new Type("Eau");
        Type plante = new Type("Plante");
        Type electrik = new Type("Electrik");
        Type roche = new Type("Roche");
        Type acier = new Type("Acier");
        Type spectre = new Type("Spectre");
        Type dragon = new Type("Dragon");
        Type tenebre = new Type("Ténèbre");
        Type fee = new Type("Fée");
        Type sol = new Type("sol");
        Type glace = new Type("glace");
        Type none = new Type("none");
        Type combat = new Type("combat");
        Type psy = new Type("psy");
        Type vol = new Type("vol");
       
        
        //creation des tableau de superiorités et inferiorités des types
        
        feu.superieur(new Type[]{plante,dragon,glace});
        feu.inferieur(new Type[]{eau,roche});
        eau.superieur(new Type[]{feu,combat,roche});
        eau.inferieur(new Type[]{plante,electrik});
        electrik.superieur(new Type[]{eau,dragon,acier,vol});
        electrik.inferieur(new Type[]{sol,roche});
        vol.superieur(new Type[]{roche,sol,plante});
        vol.inferieur(new Type[]{electrik,glace,feu});
        glace.superieur(new Type[]{dragon,vol});
        glace.inferieur(new Type[]{feu,combat});
        dragon.superieur(new Type[]{spectre,none});
        dragon.inferieur(new Type[]{electrik,glace,feu});
        acier.superieur(new Type[]{roche,sol});
        acier.inferieur(new Type[]{combat,feu,electrik});
        combat.superieur(new Type[]{glace,none,roche,acier});
        combat.inferieur(new Type[]{vol,feu,fee,psy});
        
        
        
        /// Effets d'exemple (ajuster selon la classe Effet existante)
        Effet damage = new Effet("damage");
        Effet sleep = new Effet("sleep");
        Effet brulure = new Effet("brulure");
        Effet paralysie = new Effet("paralysie");
        Effet poison = new Effet("poison");
        // les trois suivant ne seront pas utilisé
        Effet confusion = new Effet("confusion");
        Effet peur = new Effet("peur");
        Effet soin = new Effet("soin");
        

        /// Création de 50 compétences
        Competence lanceFlammes = new Competence("Lance-Flammes", feu, new Effet[]{damage,brulure}, 90);
        Competence hydrocanon = new Competence("Hydrocanon", eau, new Effet[]{damage}, 110);
        Competence fouetLianes = new Competence("Fouet Lianes", plante, new Effet[]{damage,poison}, 45);
        Competence eclair = new Competence("Éclair", electrik, new Effet[]{damage,paralysie}, 40);
        Competence charge = new Competence("Charge", none, new Effet[]{damage}, 50);
        Competence machouille = new Competence("Mâchouille", none, new Effet[]{damage,poison}, 80);
        Competence tonnerre = new Competence("Tonnerre", electrik, new Effet[]{damage,paralysie}, 90);
        Competence seisme = new Competence("Séisme", sol, new Effet[]{damage}, 100);
        Competence megaSangsue = new Competence("Méga-Sangsue", plante, new Effet[]{damage}, 75);
        Competence souffleGlace = new Competence("Souffle Glacé", glace, new Effet[]{damage}, 60);
        Competence volVie = new Competence("Vol de Vie", plante, new Effet[]{damage}, 70);
        Competence tsunami = new Competence("Tsunami", eau, new Effet[]{damage}, 130);
        Competence tornade = new Competence("Tornade", none, new Effet[]{damage}, 40);
        Competence bouleEnergie = new Competence("Boule d'Énergie", plante, new Effet[]{damage}, 90);
        Competence psyko = new Competence("Psyko", none, new Effet[]{damage,confusion}, 100);
        Competence morsure = new Competence("Morsure", none, new Effet[]{damage}, 60);
        Competence flammeche = new Competence("Flammèche", feu, new Effet[]{damage}, 40);
        Competence eboulement = new Competence("Éboulement", sol, new Effet[]{damage,sleep}, 75);
        Competence blizzard = new Competence("Blizzard", glace, new Effet[]{damage,sleep}, 120);
        Competence aquaJet = new Competence("Aqua-Jet", eau, new Effet[]{damage}, 40);
        Competence laserGlace = new Competence("Laser Glace", glace, new Effet[]{damage,sleep}, 90);
        Competence poingEclair = new Competence("Poing-Éclair", electrik, new Effet[]{damage,paralysie}, 75);
        Competence poingFeu = new Competence("Poing de Feu", feu, new Effet[]{damage,brulure}, 75);
        Competence poingPlante = new Competence("Poing de Plante", plante, new Effet[]{damage}, 75);
        Competence gigaImpact = new Competence("Giga Impact", none, new Effet[]{damage}, 150);
        Competence dracoMeteor = new Competence("Draco-Météore", dragon, new Effet[]{damage}, 130);
        Competence forceCachée = new Competence("Force Cachée", combat, new Effet[]{damage}, 60);
        Competence tonnerrePlus = new Competence("Tonnerre+", electrik, new Effet[]{damage,paralysie}, 110);
        Competence doublePied = new Competence("Double Pied", combat, new Effet[]{damage}, 30);
        Competence lameFeuille = new Competence("Lame-Feuille", plante, new Effet[]{damage}, 90);
        Competence ventViolent = new Competence("Vent Violent", none, new Effet[]{damage,confusion}, 110);
        Competence bouclier = new Competence("Bouclier", none, new Effet[]{damage}, 0);
        Competence retour = new Competence("Retour", none, new Effet[]{damage}, 102);
        Competence surf = new Competence("Surf", eau, new Effet[]{damage}, 95);
        Competence cascade = new Competence("Cascade", eau, new Effet[]{damage}, 80);
        Competence grimace = new Competence("Grimace", none, new Effet[]{damage}, 0);
        Competence voileMiroir = new Competence("Voile Miroir", none, new Effet[]{damage}, 0);
        Competence viveAttaque = new Competence("Vive-Attaque", none, new Effet[]{damage}, 40);
        Competence crochetVenin = new Competence("Crochet Venin", none, new Effet[]{damage,poison}, 50);
        Competence lancePierre = new Competence("Lance-Pierre", none, new Effet[]{damage}, 50);
        Competence rafalePsy = new Competence("Rafale Psy", psy, new Effet[]{damage,confusion}, 65);
        Competence eclairCroix = new Competence("Éclair Croix", electrik, new Effet[]{damage,paralysie}, 70);
        Competence bouclierAcier = new Competence("Bouclier d'Acier", acier, new Effet[]{damage}, 0);
        Competence poingRoche = new Competence("Poing de Roche", roche, new Effet[]{damage}, 80);
        Competence griffeSpectrale = new Competence("Griffe Spectrale", spectre, new Effet[]{damage,peur}, 70);
        Competence dracoQueue = new Competence("Draco-Queue", dragon, new Effet[]{damage}, 60);
        Competence baiserFee = new Competence("Baiser de Fée", fee, new Effet[]{damage}, 50);
        Competence tenebres = new Competence("Ombre Noire", tenebre, new Effet[]{damage,peur}, 90);
        Competence lameAcier = new Competence("Lame d'Acier", acier, new Effet[]{damage}, 100);
        Competence tempeteRocheuse = new Competence("Tempête Rocheuse", roche, new Effet[]{damage}, 110);
        Competence souffleDragon = new Competence("Souffle de Dragon", dragon, new Effet[]{damage}, 90);
        Competence chatoiement = new Competence("Chatoiement", fee, new Effet[]{damage,confusion}, 60);
        Competence cage_eclair = new Competence("Cage Eclair",electrik, new Effet[]{paralysie},0);
        Competence griffe = new Competence("Griffes",none,new Effet[]{damage},55);

        
        
        
        //Cette Section ne sert rien mise a part organiser les competences par type pour les rendres plus lisibles
        Competence[] lst_comp_none = {charge,machouille,morsure,gigaImpact,bouclier,retour,grimace,voileMiroir,viveAttaque,
                                      crochetVenin,lancePierre};
        Competence[] lst_comp_electrik = {cage_eclair,eclair,tonnerre,poingEclair,tonnerrePlus,eclairCroix};
        Competence[] lst_comp_feu = {lanceFlammes,flammeche,poingFeu};
        Competence[] lst_comp_eau = {hydrocanon,tsunami,surf,cascade};
        Competence[] lst_comp_plante = {fouetLianes,megaSangsue,volVie,poingPlante,bouleEnergie,lameFeuille};
        Competence[] lst_comp_sol ={seisme,eboulement};
        Competence[] lst_comp_combat={forceCachée,doublePied};
        Competence[] lst_comp_psy ={rafalePsy,psyko};
        Competence[] lst_comp_fee ={baiserFee,chatoiement};
        Competence[] lst_comp_glace ={souffleGlace,blizzard,laserGlace};
        Competence[] lst_comp_dragon = {dracoMeteor,dracoQueue,souffleDragon};
        Competence[] lst_comp_roche = {poingRoche,tempeteRocheuse};
        Competence[] lst_comp_acier = {bouclierAcier,lameAcier};
        Competence[] lst_comp_spectre = {griffeSpectrale};
        Competence[] lst_comp_tenebres = {tenebres};
        Competence[] lst_comp_vol = {ventViolent,tornade};
        
        
        
        
        //Dresseur
        
        Dresseur florian = new Dresseur("Florian",21,"Masculin",183.0);
        Dresseur anas = new Dresseur("Anas",21,"Masculin",180.0);        
        Dresseur adenor = new Dresseur("Adenor",21,"Feminin",175.0);
        
        
        //Creation et ajout de Pokemon au dresseurs
        
        florian.lst_pokemon[5]=new Pokemon("Herbizare",plante,none,1,0.0,535.0,50,55,40,charge,fouetLianes,megaSangsue,lameFeuille,"/assets/images/pokemons/herbizare.png");
        florian.lst_pokemon[0]=new Pokemon("Pikachu",electrik,none,1,90,535.0,20,55,40,cage_eclair,charge,eclair,poingEclair,"/assets/images/pokemons/IMG_0380.png");
        florian.lst_pokemon[2]=new Pokemon("DracoFeu",feu,dragon,1,0.0,535.0,550,55,40,dracoMeteor,lanceFlammes,griffe,grimace,"/assets/images/pokemons/IMG_0383.png");
        florian.lst_pokemon[3]=new Pokemon("evoli",none,none,1,0.0,535.0,50,55,40,charge,bouclier,viveAttaque,morsure,"/assets/images/pokemons/IMG_0387.png");
        florian.lst_pokemon[4]=new Pokemon("Roucarnage",vol,none,1,0.0,535.0,50,55,40,tornade,ventViolent,charge,morsure,"/assets/images/pokemons/roucarnage.png");
        florian.lst_pokemon[1]=new Pokemon("Macogneur",combat,none,1,0.0,535.0,50,55,40,forceCachée,doublePied,machouille,gigaImpact,"/assets/images/pokemons/macogneur.png");
        
        
        
        anas.lst_pokemon[5]=new Pokemon("ronflexe",sol,none,1,90,535.0,20,55,40,seisme,charge,eboulement,poingEclair,"/assets/images/pokemons/ronflexe.png");
        anas.lst_pokemon[4]=new Pokemon("Togekiss",vol,none,1,0.0,535.0,50,55,40,charge,tornade,ventViolent,tempeteRocheuse,"/assets/images/pokemons/togekiss.png");
        anas.lst_pokemon[2]=new Pokemon("Mewtwo",psy,none,1,0.0,535.0,50,55,40,rafalePsy,psyko,charge,morsure,"/assets/images/pokemons/IMG_0384.png");
        anas.lst_pokemon[3]=new Pokemon("Carapuce",eau,none,1,0.0,535.0,50,55,40,charge,hydrocanon,tsunami,viveAttaque,"/assets/images/pokemons/IMG_0386.png");
        anas.lst_pokemon[0]=new Pokemon("Salameche",feu,none,1,0.0,535.0,50,55,40,charge,lanceFlammes,griffe,morsure,"/assets/images/pokemons/IMG_0382.png");
        anas.lst_pokemon[1]=new Pokemon("Draco",dragon,none,1,0.0,535.0,50,55,40,dracoQueue,souffleDragon,griffe,morsure,"/assets/images/pokemons/draco.png");



        // ajout d objet
        
        florian.addhyperpot(2);
        florian.addpot(5);
        florian.addsuperpot(1);
        
        florian.addpotpara(2);
        florian.addpotpois(1);
        florian.addpotreveil(10);
        
        florian.addsuperball(2);
        florian.addhyperball(5);
        florian.addpokeball(15);        



        anas.addhyperpot(2);
        anas.addpot(5);
        anas.addsuperpot(1);  
        
        anas.addpotpara(2);
        anas.addpotpois(1);
        anas.addpotreveil(10);
        
        
        
        
        
        // Suite
        
        
        primaryStage.setTitle("AVANT");
        
        BorderPane bpp = new BorderPane();
        Button btn = new Button("Jouer");
        btn.setPrefSize(200, 200);
        bpp.setCenter(btn);
        
        
        //Intansation de class Combat et Fenetre_Combat qui sont reliée
        Combat c = new Combat(florian,anas);
        Fenetre_Combat t = new Fenetre_Combat(c);
        c.fen=t;
        // Fin Instansation
        
        
        Stage stg = new Stage();
        
        btn.setOnAction(e -> t.start(stg) );
        
        Scene scn = new Scene(bpp,400,400);
        primaryStage.setScene(scn);
        
        primaryStage.show();
        
        
        
        
    }
        
        
        
    
    }






   
    

