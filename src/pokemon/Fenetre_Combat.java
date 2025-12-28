/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Flori
 */
public class Fenetre_Combat extends Application
{
    
    Combat c;
    
    private VBox mainPane;
    
    //TESTT
    //FIN TEST
    
    
    //TOP
    Group gb0 = new Group(); 
        
    BorderPane gb_hb = new BorderPane(); //Health Bar
    BorderPane gb_fond = new BorderPane(); //Fond, pokemon, nom, lvl,...
    BorderPane bd_txt = new BorderPane(); // Pane du Text du dialogue
    
    
    //BOT
    BorderPane bpd = new BorderPane();

    
    
    
    public void SetSuperposition(){
    gb0.getChildren().addAll(gb_fond,gb_hb,bd_txt);
    }
    
    
    
    
    public Fenetre_Combat(Combat c){
        this.c = c;
    }
    
    
    
    
    
    @Override
    public void start(Stage primaryStage)
    {
        
        
        primaryStage.setTitle("interface de combat");
        mainPane = new VBox();
        
        Scene scene1 = new Scene(mainPane,500,700); // ou 512,768 convient aussi 
        primaryStage.setScene(scene1);
        ShowMainMenu();
        
        mainPane.setSpacing(0);
        
        this.SetSuperposition();

        
        mainPane.getChildren().addAll(gb0,bpd);

        
        primaryStage.show();
        

    }
        
    
    /**
     * Cette Fonction affiche le "Menu Principale" donc les bouton de base pour faire un choix, 
     * Et Par l'appel d'autre méthodes... Affiche également l'écran supérieur contenant les pokemons,
     * leur barre de vie, leur niveau ect...
     */
    public void ShowMainMenu(){
            
            //--------------------------------TOP------------------------------
            
            this.ShowAllTop();
            this.ShowHealthBar();
            this.ShowTextAttaque("Que souhaitez vous faire ?");
            
            
            
            //--------------------------------BOTTOM------------------------------
            
            
            
            VBox vbox_bottom = new VBox();
            vbox_bottom.setPrefSize(500, 350);
            
            BorderPane top_of_bottom = new BorderPane();
            AnchorPane bot_of_bottom = new AnchorPane();
            
            top_of_bottom.setPrefSize(500, 250);
            
            
            
            // BACKGROUND 
            
            Image img_fond_bot = new Image("/assets/images/interface/5.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            vbox_bottom.setBackground(background_bot);
            
            // FIN BACKGROUND
            
            
            
            
            
            
            Button btn_figth = new Button("Fight");
            btn_figth.setPrefSize(250,150);
            btn_figth.getStylesheets().add(getClass().getResource("/assets/css/fight_btn.css").toExternalForm());

            btn_figth.setOnAction(e -> ShowFigthMenu());

            
            top_of_bottom.setCenter(btn_figth);
            
            
            Button btn_objects = new Button("Objets");
            btn_objects.setPrefSize(150, 75);
            btn_objects.getStylesheets().add(getClass().getResource("/assets/css/bag_btn.css").toExternalForm());

            btn_objects.setOnAction(e -> ShowObjectMenu());
            
            
            
            
            Button btn_fuir = new Button("Fuir");
            btn_fuir.getStylesheets().add(getClass().getResource("/assets/css/run_btn.css").toExternalForm());
            btn_fuir.setPrefSize(150, 75);
            btn_fuir.setOnAction(e ->this.ShowTextAttaque("Impossible de quitter le combat"));
            
            
            
            Button btn_lst_poke = new Button("Pokemons");
            btn_lst_poke.setPrefSize(150, 75);
            btn_lst_poke.setOnAction(e -> ShowPokemonList());
            btn_lst_poke.getStylesheets().add(getClass().getResource("/assets/css/switch2.css").toExternalForm());
            
            
            
            AnchorPane.setBottomAnchor(btn_objects,10.0);
            AnchorPane.setLeftAnchor(btn_objects,10.0);


            AnchorPane.setBottomAnchor(btn_lst_poke,0.0);
            AnchorPane.setRightAnchor(btn_lst_poke,10.0);
            
            AnchorPane.setBottomAnchor(btn_fuir, -10.0);
            AnchorPane.setLeftAnchor(btn_fuir, 175.0);
            
            
            
            bot_of_bottom.getChildren().addAll(btn_fuir,btn_objects,btn_lst_poke);

            
            vbox_bottom.getChildren().addAll(top_of_bottom,bot_of_bottom);
            bpd.setCenter(vbox_bottom);
            
            

        }
    
    
    public void ShowMain(){
        this.ShowMainMenu();
        
    }
    
    
    /**
     * Cette fonction a pour principe d'afficher la partie supérieur du combat
        Elle sert a afficher le fond d'écran de combat ainsi que les pokemons, leur noms,
        leur niveaux(lvl), ainsi que leur statuts ( dors, empoisoiné,...) 
        */
    public void ShowAllTop(){
        

        Group gb1 = new Group();

        AnchorPane topPane = new AnchorPane();
        topPane.setPrefSize(500,350);




        //TOP BACKGROUND CONSTRUCTION
        
        //Image img = new Image("https://www.giantbomb.com/a/uploads/original/0/1114/207681-0_61_pokemon_pearl.jpg");
        Image img_fond_top = new Image("/assets/images/interface/3.png");
        BackgroundSize bSize_top = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_top = new Background(new BackgroundImage(img_fond_top,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_top));

        topPane.setBackground(background_top);
        
        // FIN DU BACKGROUND

        
        
        // AFFICHER LES POKEMONS
        
        
        Image img_poke1 = c.d1.lst_pokemon[0].img_pokemon;
        Image img_poke2 = c.d2.lst_pokemon[0].img_pokemon;
        
        ImageView img_poke1_view = new ImageView(img_poke1);
        ImageView img_poke2_view = new ImageView(img_poke2);
        
        img_poke1_view.setFitHeight(100);
        img_poke1_view.setFitWidth(100);
        img_poke1_view.setPreserveRatio(true);
        
        img_poke2_view.setFitHeight(100);
        img_poke2_view.setFitWidth(100);
        img_poke2_view.setPreserveRatio(true);
        
        
        AnchorPane.setBottomAnchor(img_poke1_view, 80.0);
        AnchorPane.setLeftAnchor(img_poke1_view, 100.0);            

        AnchorPane.setTopAnchor(img_poke2_view, 70.0);
        AnchorPane.setRightAnchor(img_poke2_view, 100.0);            
        
        
        
        

        // NOM des pokemons
        
        Label nom_poke1 = new Label(c.d1.lst_pokemon[0].name);
        Label nom_poke2 = new Label(c.d2.lst_pokemon[0].name);
        
        AnchorPane.setBottomAnchor(nom_poke1, 152.0);
        AnchorPane.setLeftAnchor(nom_poke1, 330.0);            

        AnchorPane.setTopAnchor(nom_poke2, 38.0);
        AnchorPane.setLeftAnchor(nom_poke2, 8.0); 
        
        ///barre d'experience (xp)
        //xp1 du joueur 1 (nous)
        
        double longeur_rect_xp1 = c.d1.lst_pokemon[0].xp/100*165;
        
        Rectangle rect_xp1_fond = new Rectangle(165,5);
        Rectangle rect_xp1 = new Rectangle(longeur_rect_xp1,3);
        
        rect_xp1_fond.setStroke(Color.BLACK);
        rect_xp1_fond.setStrokeWidth(2);
        rect_xp1_fond.setFill(Color.GAINSBORO);
        rect_xp1.setFill(Color.AQUAMARINE);
        
        AnchorPane.setBottomAnchor(rect_xp1, 108.5);
        AnchorPane.setLeftAnchor(rect_xp1, 326.0);
        AnchorPane.setBottomAnchor(rect_xp1_fond, 107.0);
        AnchorPane.setLeftAnchor(rect_xp1_fond, 325.0);
        
        
        //xp2 du joueur 2 (le bot)
        
        Rectangle rect_xp2_fond = new Rectangle(165,5);
        Rectangle rect_xp2 = new Rectangle(165,3);
        
        rect_xp2_fond.setStroke(Color.BLACK);
        rect_xp2_fond.setStrokeWidth(2);
        rect_xp2_fond.setFill(Color.GAINSBORO);
        rect_xp2.setFill(Color.AQUAMARINE);
        
        AnchorPane.setTopAnchor(rect_xp2, 89.5);
        AnchorPane.setLeftAnchor(rect_xp2, 6.0);
        
        AnchorPane.setTopAnchor(rect_xp2_fond, 88.0);
        AnchorPane.setLeftAnchor(rect_xp2_fond, 5.0);
        
        
        
        /// Effets (rectangle vert -> poison ; rouge -> Brulure ; gris -> endormis
        // les effets sont activé si les rectangles sont plein
        
        //du dresseur 1 (nous)
        
        Rectangle rect1_brulure_fond = new Rectangle(14,7);
        Rectangle rect1_poison_fond = new Rectangle(14,7);
        Rectangle rect1_sleep_fond = new Rectangle(14,7);
        
        rect1_brulure_fond.setStroke(Color.CRIMSON);
        rect1_brulure_fond.setStrokeWidth(2);
        rect1_brulure_fond.setFill(Color.TRANSPARENT);

        
        
        rect1_poison_fond.setStroke(Color.DARKGREEN);
        rect1_poison_fond.setStrokeWidth(2);
        rect1_poison_fond.setFill(Color.TRANSPARENT);

        
        rect1_sleep_fond.setStroke(Color.DARKGRAY);
        rect1_sleep_fond.setStrokeWidth(2);
        rect1_sleep_fond.setFill(Color.TRANSPARENT);
        
        
        AnchorPane.setBottomAnchor(rect1_brulure_fond, 116.0);
        AnchorPane.setLeftAnchor(rect1_brulure_fond, 450.0);
        
        
        AnchorPane.setBottomAnchor(rect1_sleep_fond, 116.0);
        AnchorPane.setLeftAnchor(rect1_sleep_fond, 484.0);
        
        
        AnchorPane.setBottomAnchor(rect1_poison_fond, 116.0);
        AnchorPane.setLeftAnchor(rect1_poison_fond, 467.0);
        
        if (c.d1.lst_pokemon[0].poisoned==true){
            rect1_poison_fond.setFill(Color.GREEN);}
        if (c.d1.lst_pokemon[0].sleeping==true){
            rect1_sleep_fond.setFill(Color.DARKGREY);}      
        if (c.d1.lst_pokemon[0].burned==true){
            rect1_brulure_fond.setFill(Color.RED);}        
        
        
        
        
        //du Dresseur 2 (enemie bot )
        Rectangle rect2_brulure_fond = new Rectangle(14,7);
        Rectangle rect2_poison_fond = new Rectangle(14,7);
        Rectangle rect2_sleep_fond = new Rectangle(14,7);
        
        rect2_brulure_fond.setStroke(Color.CRIMSON);
        rect2_brulure_fond.setStrokeWidth(2);
        rect2_brulure_fond.setFill(Color.TRANSPARENT);

        
        
        rect2_poison_fond.setStroke(Color.DARKGREEN);
        rect2_poison_fond.setStrokeWidth(2);
        rect2_poison_fond.setFill(Color.TRANSPARENT);

        
        rect2_sleep_fond.setStroke(Color.DARKGRAY);
        rect2_sleep_fond.setStrokeWidth(2);
        rect2_sleep_fond.setFill(Color.TRANSPARENT);
        
        
        AnchorPane.setTopAnchor(rect2_brulure_fond, 76.0);
        AnchorPane.setLeftAnchor(rect2_brulure_fond, 0.0);
        
       
        AnchorPane.setTopAnchor(rect2_sleep_fond, 76.0);
        AnchorPane.setLeftAnchor(rect2_sleep_fond, 17.0);
        
        
        AnchorPane.setTopAnchor(rect2_poison_fond, 76.0);
        AnchorPane.setLeftAnchor(rect2_poison_fond, 34.0);
        
        if (c.d2.lst_pokemon[0].poisoned==true){
            rect2_poison_fond.setFill(Color.GREEN);}
        if (c.d2.lst_pokemon[0].sleeping==true){
            rect2_sleep_fond.setFill(Color.DARKGREY);}      
        if (c.d2.lst_pokemon[0].burned==true){
            rect2_brulure_fond.setFill(Color.RED);}        
        
        
        
        
        // LVL 
        Label lvl_poke1 = new Label("LvL. "+c.d1.lst_pokemon[0].level);
        Label lvl_poke2 = new Label("LvL. "+c.d2.lst_pokemon[0].level);
        
        AnchorPane.setBottomAnchor(lvl_poke1, 152.0);
        AnchorPane.setLeftAnchor(lvl_poke1, 450.0);            

        AnchorPane.setTopAnchor(lvl_poke2, 38.0);
        AnchorPane.setLeftAnchor(lvl_poke2, 128.0); 
        
        
        
        
        //  AJOUT pour affichage
        topPane.getChildren().addAll(img_poke1_view,img_poke2_view,nom_poke1,nom_poke2,
                                    lvl_poke1,lvl_poke2,rect_xp1_fond,rect_xp1,rect_xp2_fond,rect_xp2,
                                    rect1_brulure_fond,rect1_sleep_fond,rect1_poison_fond,
                                    rect2_brulure_fond,rect2_sleep_fond,rect2_poison_fond);
        
        

        gb1.getChildren().addAll(topPane);
        gb_fond.setCenter(gb1);
        
    }  
    
    /**
     * Cette fonction affiche la barre de vie des pokemons
     */
    public void ShowHealthBar(){
        
        
        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(500,350);
        
        
        Label pv_poke1 = new Label("PV");
        Label pv_poke2 = new Label("PV");

        AnchorPane.setBottomAnchor(pv_poke1, 133.0);
        AnchorPane.setLeftAnchor(pv_poke1, 330.0);
        
        AnchorPane.setTopAnchor(pv_poke2, 56.0);
        AnchorPane.setLeftAnchor(pv_poke2, 7.0);
        
        Label pv_valeur_p1 = new Label(c.d1.lst_pokemon[0].hp+"/"+c.d1.lst_pokemon[0].hpmax);
        Label pv_valeur_p2 = new Label(c.d2.lst_pokemon[0].hp+"/"+c.d2.lst_pokemon[0].hpmax);
        
        
        AnchorPane.setBottomAnchor(pv_valeur_p1, 118.0);
        AnchorPane.setLeftAnchor(pv_valeur_p1, 380.0);
        
        AnchorPane.setTopAnchor(pv_valeur_p2, 70.0);
        AnchorPane.setLeftAnchor(pv_valeur_p2, 70.0);
        
        
        
        
        /// LA VRAI BARRE DE VIE
        //Du dresseur 1 (nous)
        Rectangle hb1fond = new Rectangle(130,13);
        hb1fond.setFill(Color.WHITESMOKE);
        hb1fond.setStroke(Color.BLACK);
        hb1fond.setStrokeWidth(2);
        
        double longeur_bar_vie = c.d1.lst_pokemon[0].hp/c.d1.lst_pokemon[0].hpmax*130;
        
        Rectangle hb1vie = new Rectangle(longeur_bar_vie,11);
        
        if (longeur_bar_vie <=30.0){
            hb1vie.setFill(Color.RED);
        }
        else if (longeur_bar_vie<=65){
            hb1vie.setFill((Color.ORANGE));
        }
        else {hb1vie.setFill(Color.GREEN);}
        
        
        AnchorPane.setBottomAnchor(hb1vie, 137.5);
        AnchorPane.setLeftAnchor(hb1vie, 352.0);        

        AnchorPane.setBottomAnchor(hb1fond, 135.0);
        AnchorPane.setLeftAnchor(hb1fond, 350.0);
        
        
        
        //Du dresseur 2 (bot)
        Rectangle hb2fond = new Rectangle(130,13);
        hb2fond.setFill(Color.WHITESMOKE);
        hb2fond.setStroke(Color.BLACK);
        hb2fond.setStrokeWidth(2);
        
        double longeur_bar_vie2 = c.d2.lst_pokemon[0].hp/c.d2.lst_pokemon[0].hpmax*130;
        
        Rectangle hb2vie = new Rectangle(longeur_bar_vie2,11);
        
        if (longeur_bar_vie2 <=30.0){
            hb2vie.setFill(Color.RED);
        }
        else if (longeur_bar_vie2<=65){
            hb2vie.setFill((Color.ORANGE));
        }
        else {hb2vie.setFill(Color.GREEN);}
        
        
        AnchorPane.setTopAnchor(hb2vie, 57.5);
        AnchorPane.setLeftAnchor(hb2vie, 29.6);        

        AnchorPane.setTopAnchor(hb2fond, 56.0);
        AnchorPane.setLeftAnchor(hb2fond, 28.0);
        
        
        
        // FIN BARRE DE VIE
        
        
        
        ap.getChildren().addAll(pv_poke1,pv_poke2,hb1fond,hb1vie,pv_valeur_p1,hb2fond,hb2vie,pv_valeur_p2);
        
        gb_hb.setCenter(ap);
        
        
        
    }
    
    
    

    /**
     * Cette fonction affiche l'interface de combat permettant de choisir une des quatres attaques possibles
     */
    public void ShowFigthMenu(){
            
            
            VBox split_figth = new VBox();
            
            BorderPane centre_grille = new BorderPane();
            BorderPane centre_retour = new BorderPane();
            GridPane grille_attack = new GridPane ();
            
            //BACKGROUND
            
            Image img_fond_bot = new Image("/assets/images/interface/4.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            split_figth.setBackground(background_bot);
            
            
            //FIN BAKCGROUND
            
            
            
            
            
            Button btn_attack_1 = new Button(c.d1.lst_pokemon[0].lst_competence[0].nom);
            Button btn_attack_2 = new Button(c.d1.lst_pokemon[0].lst_competence[1].nom);
            Button btn_attack_3 = new Button(c.d1.lst_pokemon[0].lst_competence[2].nom);
            Button btn_attack_4 = new Button(c.d1.lst_pokemon[0].lst_competence[3].nom);
            
            BorderPane bp1 = new BorderPane();
            BorderPane bp2 = new BorderPane();
            BorderPane bp3 = new BorderPane();
            BorderPane bp4 = new BorderPane();
            
            bp1.setCenter(btn_attack_1);
            bp2.setCenter(btn_attack_2);
            bp3.setCenter(btn_attack_3);
            bp4.setCenter(btn_attack_4);
            
            
            GridPane.setColumnIndex(bp1, 0);
            GridPane.setRowIndex(bp1, 0); 

            GridPane.setColumnIndex(bp2, 0);
            GridPane.setRowIndex(bp2, 1);

            GridPane.setColumnIndex(bp3, 1);
            GridPane.setRowIndex(bp3, 0);
            
            GridPane.setColumnIndex(bp4, 1);
            GridPane.setRowIndex(bp4, 1);
            
            bp1.setPrefSize(250,125);
            bp2.setPrefSize(250,125);            
            bp3.setPrefSize(250,125);
            bp4.setPrefSize(250,125);

            btn_attack_1.setPrefSize(200,80);
            btn_attack_2.setPrefSize(200,80);            
            btn_attack_3.setPrefSize(200,80);
            btn_attack_4.setPrefSize(200,80);
            
            btn_attack_1.getStylesheets().add(getClass().getResource("/assets/css/attaque_btn.css").toExternalForm());
            btn_attack_2.getStylesheets().add(getClass().getResource("/assets/css/attaque_btn.css").toExternalForm());
            btn_attack_3.getStylesheets().add(getClass().getResource("/assets/css/attaque_btn.css").toExternalForm());
            btn_attack_4.getStylesheets().add(getClass().getResource("/assets/css/attaque_btn.css").toExternalForm());

            
            
            btn_attack_1.setOnAction(e->c.attaque_tableau(0));
            btn_attack_2.setOnAction(e->c.attaque_tableau(1));
            btn_attack_3.setOnAction(e->c.attaque_tableau(2));
            btn_attack_4.setOnAction(e->c.attaque_tableau(3));
            
            
            grille_attack.getChildren().addAll(bp1,bp2,bp3,bp4);
            
            centre_grille.setCenter(grille_attack);
            
            
            // BTN RETOUR
            
            
            Button btn_retour_main = new Button("Back");
            btn_retour_main.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

            btn_retour_main.setOnAction(e -> ShowMainMenu());
            btn_retour_main.setPrefSize(300,50);
            
            centre_retour.setPrefSize(500,100);
            centre_retour.setCenter(btn_retour_main);
            
            
            split_figth.getChildren().addAll(centre_grille,centre_retour);
            
            bpd.setCenter(split_figth);
            
            
        }
        
    /**
         * Cette fonction affiche l'interface permettant de choisir quel type d'objet choisir
         */
    public void ShowObjectMenu(){
            
            VBox menu_objects = new VBox();
            GridPane grille_btn = new GridPane();
            BorderPane bp_retour = new BorderPane();
            
            
            
            // BACKGROUND 
            
            Image img_fond_bot = new Image("/assets/images/interface/4.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            menu_objects.setBackground(background_bot);
            
            
            // FIN BACKGROUND
            
            
            // Grille de boutons
            BorderPane bp1 = new BorderPane();
            BorderPane bp2 = new BorderPane();
            BorderPane bp3 = new BorderPane();
            BorderPane bp4 = new BorderPane();
            
            bp1.setPrefSize(250, 125);
            bp2.setPrefSize(250, 125);
            bp3.setPrefSize(250, 125);
            bp4.setPrefSize(250, 125);
            
            Button potion_soins = new Button("POTIONS DE SOINS");
            Button potion_statut = new Button("POTIONS DE STATUTS");
            Button balls = new Button("BALLS");
            Button autre = new Button("AUTRE");
            
            potion_soins.setPrefSize(200, 100);
            potion_statut.setPrefSize(200, 100);
            balls.setPrefSize(200, 100);
            autre.setPrefSize(200, 100);
            
            potion_soins.getStylesheets().add(getClass().getResource("/assets/css/pot.css").toExternalForm());
            potion_statut.getStylesheets().add(getClass().getResource("/assets/css/pot.css").toExternalForm());
            balls.getStylesheets().add(getClass().getResource("/assets/css/pot.css").toExternalForm());
            autre.getStylesheets().add(getClass().getResource("/assets/css/pot.css").toExternalForm());

            
            potion_soins.setOnAction(e -> this.ShowHealPotion());
            potion_statut.setOnAction(e -> this.ShowStatPotion());
            balls.setOnAction(e -> this.ShowBalls());
            autre.setDisable(true);
            
            
            bp1.setCenter(potion_soins);
            bp2.setCenter(potion_statut);
            bp3.setCenter(balls);
            bp4.setCenter(autre);
            
            
            
            GridPane.setColumnIndex(bp1, 0);
            GridPane.setRowIndex(bp1, 0);

            GridPane.setColumnIndex(bp2, 0);
            GridPane.setRowIndex(bp2, 1);

            GridPane.setColumnIndex(bp3, 1);
            GridPane.setRowIndex(bp3, 0);
            
            GridPane.setColumnIndex(bp4, 1);
            GridPane.setRowIndex(bp4, 1);
            
            grille_btn.getChildren().addAll(bp1,bp2,bp3,bp4);
            
            
            // Bas pour le retour
            
            bp_retour.setPrefSize(500,100);
            Button btn_retour_main = new Button("back");
            btn_retour_main.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

            btn_retour_main.setPrefSize(300, 80);
            btn_retour_main.setOnAction(e -> ShowMainMenu());
            
            
            bp_retour.setCenter(btn_retour_main);
            
            
            menu_objects.getChildren().addAll(grille_btn,bp_retour);
            bpd.setCenter(menu_objects);
            
            
        }
        
    /**
     * Cette fonction affiche l'interface permettant de changer de pokemon
     */
    public void ShowPokemonList(){
            
            
            VBox split_figth = new VBox();
            
            BorderPane bptxt = new BorderPane();
            Label txt = new Label("Veuillez choisir un pokemon");
            bptxt.setCenter(txt);
            bptxt.setPrefHeight(65);
            
            BorderPane centre_grille = new BorderPane();
            BorderPane centre_retour = new BorderPane();
            GridPane grille_attack = new GridPane ();
            grille_attack.setPrefSize(500, 225);
            
            //BACKGROUND
            
            Image img_fond_bot = new Image("/assets/images/interface/4.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            split_figth.setBackground(background_bot);
            
            
            //FIN BAKCGROUND
            
            
            for (int i = 0; i<c.d1.lst_pokemon.length;i++){
                
                switch(i){
                    case 0:
                        if (c.d1.lst_pokemon[0]!=null){
                            Button btn_poke1 = new Button(c.d1.lst_pokemon[0].name +"  " +c.d1.lst_pokemon[0].hp+"/"+c.d1.lst_pokemon[0].hpmax+" HP");
                            BorderPane bp1 = new BorderPane();
                            bp1.setCenter(btn_poke1);
                            GridPane.setColumnIndex(bp1, 0);
                            GridPane.setRowIndex(bp1, 0);
                            bp1.setPrefSize(250,75);
                            btn_poke1.setPrefSize(200,70);
                            btn_poke1.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp1);
                            
                            btn_poke1.setOnAction(e -> c.switch_tableau("0"));
                            
                            btn_poke1.setDisable(true);
                            
                            break;
                        }
                    case 1:
                        if (c.d1.lst_pokemon[1]!=null){
                            Button btn_poke2 = new Button(c.d1.lst_pokemon[1].name+"  " +c.d1.lst_pokemon[1].hp+"/"+c.d1.lst_pokemon[1].hpmax+" HP");
                            BorderPane bp2 = new BorderPane();
                            bp2.setCenter(btn_poke2);
                            GridPane.setColumnIndex(bp2, 0);
                            GridPane.setRowIndex(bp2, 1);
                            bp2.setPrefSize(250,75);
                            btn_poke2.setPrefSize(200,70);
                            btn_poke2.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp2);
                            
                            
                            if (c.d1.lst_pokemon[1].ko==true){
                                btn_poke2.setDisable(true);
                            }
                            
                            btn_poke2.setOnAction(e -> c.switch_tableau("1"));
                            
                            break;
                        }
                    case 2:
                        if (c.d1.lst_pokemon[2]!=null){
                            Button btn_poke3 = new Button(c.d1.lst_pokemon[2].name +"  " +c.d1.lst_pokemon[2].hp+"/"+c.d1.lst_pokemon[2].hpmax+" HP");
                            BorderPane bp3 = new BorderPane();
                            bp3.setCenter(btn_poke3);
                            GridPane.setColumnIndex(bp3, 1);
                            GridPane.setRowIndex(bp3, 0);
                            bp3.setPrefSize(250,70);
                            btn_poke3.setPrefSize(200,75);
                            btn_poke3.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp3);

                            if (c.d1.lst_pokemon[2].ko==true){
                                btn_poke3.setDisable(true);
                            }
                            
                            btn_poke3.setOnAction(e -> c.switch_tableau("2"));
                            
                            break;
                        }
                    case 3:
                        if (c.d1.lst_pokemon[3]!=null){
                            Button btn_poke4 = new Button(c.d1.lst_pokemon[3].name+"  " +c.d1.lst_pokemon[3].hp+"/"+c.d1.lst_pokemon[3].hpmax+" HP");
                            BorderPane bp4 = new BorderPane();
                            bp4.setCenter(btn_poke4);
                            GridPane.setColumnIndex(bp4, 1);
                            GridPane.setRowIndex(bp4, 1);
                            bp4.setPrefSize(250,70);
                            btn_poke4.setPrefSize(200,75);
                            btn_poke4.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp4);
                            
                            if (c.d1.lst_pokemon[3].ko==true){
                                btn_poke4.setDisable(true);
                            }
                            
                            btn_poke4.setOnAction(e -> c.switch_tableau("3"));
                            
                            break;
                        }
                    case 4:
                        if (c.d1.lst_pokemon[4]!=null){
                            Button btn_poke5 = new Button(c.d1.lst_pokemon[4].name +"  " +c.d1.lst_pokemon[4].hp+"/"+c.d1.lst_pokemon[4].hpmax+" HP");
                            BorderPane bp5 = new BorderPane();
                            bp5.setCenter(btn_poke5);
                            GridPane.setColumnIndex(bp5, 1);
                            GridPane.setRowIndex(bp5, 2);
                            bp5.setPrefSize(250,70);
                            btn_poke5.setPrefSize(200,75);
                            btn_poke5.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp5);
                            
                            if (c.d1.lst_pokemon[4].ko==true){
                                btn_poke5.setDisable(true);
                            }
                            
                            btn_poke5.setOnAction(e -> c.switch_tableau("4"));
                            
                            break;
                        }
                    case 5:
                        if (c.d1.lst_pokemon[5]!=null){
                            Button btn_poke6 = new Button(c.d1.lst_pokemon[5].name +"  " +c.d1.lst_pokemon[5].hp+"/"+c.d1.lst_pokemon[5].hpmax+" HP");
                            BorderPane bp6 = new BorderPane();
                            bp6.setCenter(btn_poke6);
                            GridPane.setColumnIndex(bp6, 0);
                            GridPane.setRowIndex(bp6, 2);
                            bp6.setPrefSize(250,70);
                            btn_poke6.setPrefSize(200,75);
                            btn_poke6.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp6);
                            
                            if (c.d1.lst_pokemon[5].ko==true){
                                btn_poke6.setDisable(true);
                            }
                            
                            btn_poke6.setOnAction(e -> c.switch_tableau("5"));
                            
                            break;
                        }
                        
                }
                
            }
            
            
            centre_grille.setCenter(grille_attack);
            
            Button btn_retour_main = new Button("Back");
            btn_retour_main.setOnAction(e -> ShowMainMenu());
            btn_retour_main.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

            btn_retour_main.setPrefSize(300,40);
            
            centre_retour.setPrefSize(500,75);
            centre_retour.setCenter(btn_retour_main);
            
            
            split_figth.getChildren().addAll(bptxt,centre_grille,centre_retour);
            
            bpd.setCenter(split_figth);
            
            
        }
    
    
    /**
     * Cette fonction est appelée après avoir choisit un objet. On doit choisir sur 
     * quel pokemon nous souhaitons appliquer l'objet, elle est la pour cela.
     */
    public void Show_attribut_objet_poke(int ligne, int colonne){
            
            
            VBox split_figth = new VBox();
            
            BorderPane bptxt = new BorderPane();
            Label txt = new Label("Veuillez choisir un pokemon");
            bptxt.setCenter(txt);
            bptxt.setPrefHeight(65);
            
            BorderPane centre_grille = new BorderPane();
            BorderPane centre_retour = new BorderPane();
            GridPane grille_attack = new GridPane ();
            grille_attack.setPrefSize(500, 225);
            
            //BACKGROUND
            
            Image img_fond_bot = new Image("/assets/images/interface/4.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            split_figth.setBackground(background_bot);
            
            
            //FIN BAKCGROUND
            
            
            for (int i = 0; i<c.d1.lst_pokemon.length;i++){
                
                switch(i){
                    case 0:
                        if (c.d1.lst_pokemon[0]!=null){
                            Button btn_poke1 = new Button(c.d1.lst_pokemon[0].name +"  " +c.d1.lst_pokemon[0].hp+"/"+c.d1.lst_pokemon[0].hpmax+" HP");
                            BorderPane bp1 = new BorderPane();
                            bp1.setCenter(btn_poke1);
                            GridPane.setColumnIndex(bp1, 0);
                            GridPane.setRowIndex(bp1, 0);
                            bp1.setPrefSize(250,75);
                            btn_poke1.setPrefSize(200,70);
                            btn_poke1.setOnAction(e -> c.objet_tableau(ligne, colonne, 0) );

                            btn_poke1.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp1);
                            
                            
                            
                            break;
                        }
                    case 1:
                        if (c.d1.lst_pokemon[1]!=null){
                            Button btn_poke2 = new Button(c.d1.lst_pokemon[1].name+"  " +c.d1.lst_pokemon[1].hp+"/"+c.d1.lst_pokemon[1].hpmax+" HP");
                            BorderPane bp2 = new BorderPane();
                            bp2.setCenter(btn_poke2);
                            GridPane.setColumnIndex(bp2, 0);
                            GridPane.setRowIndex(bp2, 1);
                            bp2.setPrefSize(250,75);
                            btn_poke2.setPrefSize(200,70);
                            btn_poke2.setOnAction(e -> c.objet_tableau(ligne, colonne, 1) );
                            btn_poke2.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp2);
                            
                            
                            if (c.d1.lst_pokemon[1].ko==true){
                                btn_poke2.setDisable(true);
                            }
                            
                            break;
                        }
                    case 2:
                        if (c.d1.lst_pokemon[2]!=null){
                            Button btn_poke3 = new Button(c.d1.lst_pokemon[2].name +"  " +c.d1.lst_pokemon[2].hp+"/"+c.d1.lst_pokemon[2].hpmax+" HP");
                            BorderPane bp3 = new BorderPane();
                            bp3.setCenter(btn_poke3);
                            GridPane.setColumnIndex(bp3, 1);
                            GridPane.setRowIndex(bp3, 0);
                            bp3.setPrefSize(250,70);
                            btn_poke3.setPrefSize(200,75);
                            btn_poke3.setOnAction(e -> c.objet_tableau(ligne, colonne, 2) );
                            btn_poke3.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp3);

                            if (c.d1.lst_pokemon[2].ko==true){
                                btn_poke3.setDisable(true);
                            }
                            
                            break;
                        }
                    case 3:
                        if (c.d1.lst_pokemon[3]!=null){
                            Button btn_poke4 = new Button(c.d1.lst_pokemon[3].name+"  " +c.d1.lst_pokemon[3].hp+"/"+c.d1.lst_pokemon[3].hpmax+" HP");
                            BorderPane bp4 = new BorderPane();
                            bp4.setCenter(btn_poke4);
                            GridPane.setColumnIndex(bp4, 1);
                            GridPane.setRowIndex(bp4, 1);
                            bp4.setPrefSize(250,70);
                            btn_poke4.setPrefSize(200,75);
                            btn_poke4.setOnAction(e -> c.objet_tableau(ligne, colonne, 3) );
                            btn_poke4.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp4);
                            
                            if (c.d1.lst_pokemon[3].ko==true){
                                btn_poke4.setDisable(true);
                            }
                            
                            break;
                        }
                    case 4:
                        if (c.d1.lst_pokemon[4]!=null){
                            Button btn_poke5 = new Button(c.d1.lst_pokemon[4].name +"  " +c.d1.lst_pokemon[4].hp+"/"+c.d1.lst_pokemon[4].hpmax+" HP");
                            BorderPane bp5 = new BorderPane();
                            bp5.setCenter(btn_poke5);
                            GridPane.setColumnIndex(bp5, 1);
                            GridPane.setRowIndex(bp5, 2);
                            bp5.setPrefSize(250,70);
                            btn_poke5.setPrefSize(200,75);
                            btn_poke5.setOnAction(e -> c.objet_tableau(ligne, colonne, 4) );
                            btn_poke5.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp5);
                            
                            if (c.d1.lst_pokemon[4].ko==true){
                                btn_poke5.setDisable(true);
                            }
                            
                            break;
                        }
                    case 5:
                        if (c.d1.lst_pokemon[5]!=null){
                            Button btn_poke6 = new Button(c.d1.lst_pokemon[5].name +"  " +c.d1.lst_pokemon[5].hp+"/"+c.d1.lst_pokemon[5].hpmax+" HP");
                            BorderPane bp6 = new BorderPane();
                            bp6.setCenter(btn_poke6);
                            GridPane.setColumnIndex(bp6, 0);
                            GridPane.setRowIndex(bp6, 2);
                            bp6.setPrefSize(250,70);
                            btn_poke6.setPrefSize(200,75);
                            btn_poke6.setOnAction(e -> c.objet_tableau(ligne, colonne, 5) );
                            btn_poke6.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp6);
                            
                            if (c.d1.lst_pokemon[5].ko==true){
                                btn_poke6.setDisable(true);
                            }
                            
                            break;
                        }
                        
                }
                
            }
            
            
            centre_grille.setCenter(grille_attack);
            
            Button btn_retour_main = new Button("Back");
            btn_retour_main.setOnAction(e -> ShowMainMenu());
            btn_retour_main.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

            btn_retour_main.setPrefSize(300,40);
            
            centre_retour.setPrefSize(500,75);
            centre_retour.setCenter(btn_retour_main);
            
            
            split_figth.getChildren().addAll(bptxt,centre_grille,centre_retour);
            
            bpd.setCenter(split_figth);
            
            
        }
     
    /**
     * Cette fonction affiche la liste de pokemons disponible (non ko) une fois le notre KO 
     */
    public void ShowPokemonList_by_ko(){
            
            
            VBox split_figth = new VBox();
            
            BorderPane bptxt = new BorderPane();
            Label txt = new Label("Veuillez choisir un pokemon");
            bptxt.setCenter(txt);
            bptxt.setPrefHeight(65);
            
            BorderPane centre_grille = new BorderPane();
            BorderPane centre_retour = new BorderPane();
            GridPane grille_attack = new GridPane ();
            grille_attack.setPrefSize(500, 225);
            
            //BACKGROUND
            
            Image img_fond_bot = new Image("/assets/images/interface/4.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            split_figth.setBackground(background_bot);
            
            
            //FIN BAKCGROUND
            
            
            for (int i = 0; i<c.d1.lst_pokemon.length;i++){
                
                switch(i){
                    case 0:
                        if (c.d1.lst_pokemon[0]!=null){
                            Button btn_poke1 = new Button(c.d1.lst_pokemon[0].name +"  " +c.d1.lst_pokemon[0].hp+"/"+c.d1.lst_pokemon[0].hpmax+" HP");
                            BorderPane bp1 = new BorderPane();
                            bp1.setCenter(btn_poke1);
                            GridPane.setColumnIndex(bp1, 0);
                            GridPane.setRowIndex(bp1, 0);
                            bp1.setPrefSize(250,75);
                            btn_poke1.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            btn_poke1.setPrefSize(200,70);
                            grille_attack.getChildren().add(bp1);
                            
                            btn_poke1.setDisable(true);
                            
                            
                            break;
                        }
                    case 1:
                        if (c.d1.lst_pokemon[1]!=null){
                            Button btn_poke2 = new Button(c.d1.lst_pokemon[1].name+"  " +c.d1.lst_pokemon[1].hp+"/"+c.d1.lst_pokemon[1].hpmax+" HP");
                            BorderPane bp2 = new BorderPane();
                            bp2.setCenter(btn_poke2);
                            GridPane.setColumnIndex(bp2, 0);
                            GridPane.setRowIndex(bp2, 1);
                            bp2.setPrefSize(250,75);
                            btn_poke2.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            btn_poke2.setPrefSize(200,70);
                            grille_attack.getChildren().add(bp2);
                            
                            btn_poke2.setOnAction(e -> {this.Switch(c.d1, "1");
                                                        this.ShowMain();});
                            
                            if (c.d1.lst_pokemon[1].ko==true){
                                btn_poke2.setDisable(true);
                            }
                            
                            
                            break;
                        }
                    case 2:
                        if (c.d1.lst_pokemon[2]!=null){
                            Button btn_poke3 = new Button(c.d1.lst_pokemon[2].name +"  " +c.d1.lst_pokemon[2].hp+"/"+c.d1.lst_pokemon[2].hpmax+" HP");
                            BorderPane bp3 = new BorderPane();
                            bp3.setCenter(btn_poke3);
                            GridPane.setColumnIndex(bp3, 1);
                            GridPane.setRowIndex(bp3, 0);
                            bp3.setPrefSize(250,70);
                            btn_poke3.setPrefSize(200,75);
                            btn_poke3.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp3);
                            
                            btn_poke3.setOnAction(e -> {this.Switch(c.d1, "2");
                                                        this.ShowMain();});
                            
                            if (c.d1.lst_pokemon[2].ko==true){
                                btn_poke3.setDisable(true);
                            }
                            
                            break;
                        }
                    case 3:
                        if (c.d1.lst_pokemon[3]!=null){
                            Button btn_poke4 = new Button(c.d1.lst_pokemon[3].name+"  " +c.d1.lst_pokemon[3].hp+"/"+c.d1.lst_pokemon[3].hpmax+" HP");
                            BorderPane bp4 = new BorderPane();
                            bp4.setCenter(btn_poke4);
                            GridPane.setColumnIndex(bp4, 1);
                            GridPane.setRowIndex(bp4, 1);
                            bp4.setPrefSize(250,70);
                            btn_poke4.setPrefSize(200,75);
                            btn_poke4.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());
                            grille_attack.getChildren().add(bp4);
                            
                            btn_poke4.setOnAction(e -> {this.Switch(c.d1, "3");
                                                        this.ShowMain();});
                            
                            if (c.d1.lst_pokemon[3].ko==true){
                                btn_poke4.setDisable(true);
                            }
                            
                            break;
                        }
                    case 4:
                        if (c.d1.lst_pokemon[4]!=null){
                            Button btn_poke5 = new Button(c.d1.lst_pokemon[4].name +"  " +c.d1.lst_pokemon[4].hp+"/"+c.d1.lst_pokemon[4].hpmax+" HP");
                            BorderPane bp5 = new BorderPane();
                            bp5.setCenter(btn_poke5);
                            GridPane.setColumnIndex(bp5, 1);
                            GridPane.setRowIndex(bp5, 2);
                            bp5.setPrefSize(250,70);
                            btn_poke5.setPrefSize(200,75);
                            btn_poke5.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp5);
                            
                            btn_poke5.setOnAction(e ->{ this.Switch(c.d1, "4");
                                                        this.ShowMain();});
                            
                            if (c.d1.lst_pokemon[4].ko==true){
                                btn_poke5.setDisable(true);
                            }
                            
                            break;
                        }
                    case 5:
                        if (c.d1.lst_pokemon[5]!=null){
                            Button btn_poke6 = new Button(c.d1.lst_pokemon[5].name +"  " +c.d1.lst_pokemon[5].hp+"/"+c.d1.lst_pokemon[5].hpmax+" HP");
                            BorderPane bp6 = new BorderPane();
                            bp6.setCenter(btn_poke6);
                            GridPane.setColumnIndex(bp6, 0);
                            GridPane.setRowIndex(bp6, 2);
                            bp6.setPrefSize(250,70);
                            btn_poke6.setPrefSize(200,75);
                            btn_poke6.getStylesheets().add(getClass().getResource("/assets/css/switch.css").toExternalForm());

                            grille_attack.getChildren().add(bp6);
                            
                            btn_poke6.setOnAction(e -> {this.Switch(c.d1, "5");
                                                        this.ShowMain();});
                            
                            if (c.d1.lst_pokemon[5].ko==true){
                                btn_poke6.setDisable(true);
                            }
                            
                            break;
                        }
                        
                }
                
            }
            
            
            centre_grille.setCenter(grille_attack);
            
            Button btn_retour_main = new Button("Back");
            btn_retour_main.setDisable(true);
            btn_retour_main.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

            btn_retour_main.setPrefSize(300,40);
            
            centre_retour.setPrefSize(500,75);
            centre_retour.setCenter(btn_retour_main);
            
            
            split_figth.getChildren().addAll(bptxt,centre_grille,centre_retour);
            
            bpd.setCenter(split_figth);
            
            
        }
    
    
    
    /**
     * Cette fonction permet de changer de pokemon
     */
    public void Switch( Dresseur d, String x){
        
        Pokemon intermediaire = d.lst_pokemon[0];
        d.lst_pokemon[0]=d.lst_pokemon[parseInt(x)];
        d.lst_pokemon[parseInt(x)]=intermediaire;
        
        
    }
    
    
    
    /**
     *Cette fonction permet d'afficher des "Labels" (textes) dans le cadre supérieur 
     */
    public void ShowTextAttaque(String s){
        
        AnchorPane ap_dia = new AnchorPane();
        ap_dia.setPrefSize(500,350);

        Label txt = new Label (s);
        BorderPane centre_txt = new BorderPane();
        centre_txt.setPrefSize(350, 60);
        
        
        txt.setWrapText(true);
        centre_txt.setCenter(txt);
        
        
        AnchorPane.setBottomAnchor(centre_txt, 10.0);
        AnchorPane.setRightAnchor(centre_txt, 75.0);
        ap_dia.getChildren().add(centre_txt);
        bd_txt.setCenter(ap_dia);
        
        
        
    }
 
        
    
    /**
     * Cette fonction sert a afficher juste un fond d'écran en attendant que les message se passe
     */
    public void ShowDownWait(){
        
        BorderPane bp_wait = new BorderPane();
        bp_wait.setPrefSize(500,350);
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));

        bp_wait.setBackground(background_bot);


        //FIN BAKCGROUND
        
        
        Button ok = new Button("ok");
        
        
        ok.setPrefSize(300, 200);
        
        bp_wait.setCenter(ok);
        
        bpd.setCenter(bp_wait);
        
        
    }
    
    
    /**
     * Cette Fonction Affiche le resultat d'un combat lorsque celui-ci est fini ( un des deux dresseur n'a plus de pokemon en état de se battre)
     * @param b 
     */
    public void Fin_combat(boolean b){
        
        BorderPane bp_wait = new BorderPane();
        bp_wait.setPrefSize(500,350);
        int a;
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));

        bp_wait.setBackground(background_bot);


        //FIN BAKCGROUND

        
        Button quit = new Button("quitter");
        
        
        quit.setPrefSize(300, 200);
        
        bp_wait.setCenter(quit);
        bpd.setCenter(bp_wait);
        
        
        
    }   
    
    
    /**
     * Cette fonction est récursive, Elle affiche les messages décrivant une étape de combat ( ou round ) et les fait défiler à l'aide de boutons
     */
    public void Showdefile(ArrayList<String> t, int indice, Ordi o){
        
        BorderPane bp_wait = new BorderPane();
        bp_wait.setPrefSize(500,350);
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot, BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));
        bp_wait.setBackground(background_bot);
        //FIN BAKCGROUND
        if (indice==t.size()){
            Button bn = new Button("suivant");
            bn.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());
            bn.setPrefSize(200, 100);
            if (c.d2.is_over()==true){
                bn.setOnAction(e->this.Fin_combat(true));
            }
            else if (c.d2.lst_pokemon[0].ko==true){
                ShowTextAttaque("Le poke adverse est ko");
                o.switch_by_ko_ordi(c.d2, c.d1,c.fen);
                bn.setOnAction(e ->this.ShowMainMenu());
            }
            else if (c.d1.is_over()==true){
                bn.setOnAction(e->this.Fin_combat(false));
            }
            else if (c.d1.lst_pokemon[0].ko==true){
                ShowTextAttaque("votre pokemon est ko");
                bn.setOnAction(e ->this.ShowPokemonList_by_ko());
            }
            else {
                ShowTextAttaque("Continuer le combat");
                bn.setOnAction(e ->{this.ShowMainMenu();});}
            bp_wait.setCenter(bn);
            bpd.setCenter(bp_wait);}
        
        
        
        else {
            Button bn = new Button("suivant");
            bn.setPrefSize(200, 100);
            bn.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

            bn.setOnAction(e -> {ShowTextAttaque(t.get(indice)); Showdefile(t,indice+1,o); if (1==1) {this.ShowHealthBar();};});
            
            bp_wait.setCenter(bn);
            bpd.setCenter(bp_wait);
        }
        
        
    }
    
    
    
    
    
    /**
     * Cette fonction affiche l'interface des potions utilisable
     */
    public void ShowHealPotion(){
        
        VBox potlst_et_back = new VBox();
        potlst_et_back.setPrefSize(500,350);
        
        BorderPane marge = new BorderPane();
        marge.setPrefSize(500, 80);
        
        Label txtmarge= new Label("Choisissez une potion");
        marge.setCenter(txtmarge);
        
        BorderPane bp_back =new BorderPane();
        bp_back.setPrefSize(500, 70);
        bp_back.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

        
        Button btn_back = new Button("Back");
        btn_back.setPrefSize(100, 50);
        btn_back.setOnAction(e -> this.ShowObjectMenu());
        
        bp_back.setCenter(btn_back);
        
        
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));

        potlst_et_back.setBackground(background_bot);


        //FIN BAKCGROUND
        
        
        
        BorderPane centre = new BorderPane();
        centre.setPrefSize(500,200);
        

        
        VBox vb_btn = new VBox(10);
        vb_btn.setPrefSize(400, 200);
        
        
        for (int i=0 ; i<c.d1.lst_obj[0].length;i++){
            switch(i){
                case 0:
                    if (c.d1.lst_obj[0][0]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/healing/potionn.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b1 = new Button("Potion"+" x"+c.d1.lst_obj[0][0]);
                        b1.setPrefSize(400, 90);
                        b1.setOnAction(e -> this.Show_attribut_objet_poke(0, 0) );
                        b1.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        BorderPane bp1 = new BorderPane();
                        bp1.setCenter(b1);
                        bp1.setRight(img);
                        bp1.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp1);
                        break;
                        }
                case 1 :
                    if (c.d1.lst_obj[0][1]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/healing/superpotion.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b2 = new Button("Super Potion"+" x"+c.d1.lst_obj[0][1]);
                        b2.setPrefSize(400, 90);
                        b2.setOnAction(e -> this.Show_attribut_objet_poke(0, 1) );
                        b2.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());
                        
                        BorderPane bp2 = new BorderPane();
                        bp2.setCenter(b2);
                        bp2.setRight(img);
                        bp2.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp2);
                        break;
                        }
                case 2:
                    if (c.d1.lst_obj[0][2]!=0) {
                        
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/healing/hyperpotion.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        Button b3 = new Button("Hyper Potion"+" x"+c.d1.lst_obj[0][2]);
                        b3.setPrefSize(400, 90);
                        b3.setOnAction(e -> this.Show_attribut_objet_poke(0, 2) );
                        b3.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp3 = new BorderPane();
                        bp3.setCenter(b3);
                        bp3.setRight(img);
                        bp3.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp3);
                        break;
                        }
                    
            }
            
        }
        
        
        
        
        
        centre.setCenter(vb_btn);

        
        potlst_et_back.getChildren().addAll(marge,centre,bp_back);
        bpd.setCenter(potlst_et_back);
        
        
        
        
        
        
        
    }
    
    /**
     * Cette fonction affiche l'interface des potions de statuts utilisable 
     */
    public void ShowStatPotion(){
        
        VBox potlst_et_back = new VBox();
        potlst_et_back.setPrefSize(500,350);
        
        BorderPane marge = new BorderPane();
        marge.setPrefSize(500, 80);
        
        Label txtmarge= new Label("Choisissez une potion");
        marge.setCenter(txtmarge);
        
        BorderPane bp_back =new BorderPane();
        bp_back.setPrefSize(500, 70);
        bp_back.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

        
        Button btn_back = new Button("Back");
        btn_back.setPrefSize(100, 50);
        btn_back.setOnAction(e -> this.ShowObjectMenu());
        
        bp_back.setCenter(btn_back);
        
        
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));

        potlst_et_back.setBackground(background_bot);


        //FIN BAKCGROUND
        
        
        
        BorderPane centre = new BorderPane();
        centre.setPrefSize(500,200);
        

        
        VBox vb_btn = new VBox(10);
        vb_btn.setPrefSize(400, 200);
        
        
        for (int i=0 ; i<c.d1.lst_obj[1].length;i++){
            switch(i){
                case 0:
                    if (c.d1.lst_obj[1][0]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/other/sleep.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b1 = new Button("Potion réveil"+" x"+c.d1.lst_obj[1][0]);
                        b1.setPrefSize(400, 90);
                        b1.setOnAction(e -> this.Show_attribut_objet_poke(1, 0) );
                        b1.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp1 = new BorderPane();
                        bp1.setCenter(b1);
                        bp1.setRight(img);
                        bp1.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp1);
                        break;
                        }
                case 1 :
                    if (c.d1.lst_obj[1][1]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/other/antidote.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b2 = new Button("Potion anti-poison"+" x"+c.d1.lst_obj[1][1]);
                        b2.setPrefSize(400, 90);
                        b2.setOnAction(e -> this.Show_attribut_objet_poke(1, 1) );
                        b2.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp2 = new BorderPane();
                        bp2.setCenter(b2);
                        bp2.setRight(img);
                        bp2.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp2);
                        break;
                        }
                case 2:
                    if (c.d1.lst_obj[1][2]!=0) {
                        
                        
                        ImageView img = new ImageView(new Image("/assets/images/potions/other/paralyse.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        Button b3 = new Button("Potion anti-paralyse"+" x"+c.d1.lst_obj[1][2]);
                        b3.setPrefSize(400, 90);
                        b3.setOnAction(e -> this.Show_attribut_objet_poke(1, 2) );
                        b3.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp3 = new BorderPane();
                        bp3.setCenter(b3);
                        bp3.setRight(img);
                        bp3.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp3);
                        break;
                        }
                    
            }
            
        }
        
        
        
        
        
        centre.setCenter(vb_btn);

        
        potlst_et_back.getChildren().addAll(marge,centre,bp_back);
        bpd.setCenter(potlst_et_back);
        
        
        
        
        
        
        
    }
    
    /**
     * Cette fonction affiche l'interface des pokéballs, Cependant celles ci ne sont pas utilisables
     */
    public void ShowBalls(){
        
        VBox potlst_et_back = new VBox();
        potlst_et_back.setPrefSize(500,350);
        
        BorderPane marge = new BorderPane();
        marge.setPrefSize(500, 80);
        
        Label txtmarge= new Label("Choisissez une pokéball");
        marge.setCenter(txtmarge);
        
        BorderPane bp_back =new BorderPane();
        bp_back.setPrefSize(500, 70);
        bp_back.getStylesheets().add(getClass().getResource("/assets/css/back.css").toExternalForm());

        
        Button btn_back = new Button("Back");
        btn_back.setPrefSize(100, 50);
        btn_back.setOnAction(e -> this.ShowObjectMenu());
        
        bp_back.setCenter(btn_back);
        
        
        //BACKGROUND
            
        Image img_fond_bot = new Image("/assets/images/interface/4.png");
        BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);

        Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundRepeat.NO_REPEAT,
                                                                        BackgroundPosition.CENTER,
                                                                        bSize_bot));

        potlst_et_back.setBackground(background_bot);


        //FIN BAKCGROUND
        
        
        
        BorderPane centre = new BorderPane();
        centre.setPrefSize(500,200);
        

        
        VBox vb_btn = new VBox(10);
        vb_btn.setPrefSize(400, 200);
        
        
        for (int i=0 ; i<c.d1.lst_obj[0].length;i++){
            switch(i){
                case 0:
                    if (c.d1.lst_obj[0][0]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/pokeballs/pokeball2.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b1 = new Button("pokeball"+" x"+c.d1.lst_obj[2][0]);
                        b1.setPrefSize(400, 90);
                        b1.setOnAction(e -> this.ShowTextAttaque("Cette fonctionalité n'est pas disponible en combat") );
                        b1.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp1 = new BorderPane();
                        bp1.setCenter(b1);
                        bp1.setRight(img);
                        bp1.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp1);
                        break;
                        }
                case 1 :
                    if (c.d1.lst_obj[0][1]!=0) {
                        
                        ImageView img = new ImageView(new Image("/assets/images/pokeballs/superball2.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        
                        Button b2 = new Button("Super-Ball"+" x"+c.d1.lst_obj[2][1]);
                        b2.setPrefSize(400, 90);
                        b2.setOnAction(e -> this.ShowTextAttaque("Cette fonctionalité n'est pas disponible en combat") );
                        b2.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp2 = new BorderPane();
                        bp2.setCenter(b2);
                        bp2.setRight(img);
                        bp2.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp2);
                        break;
                        }
                case 2:
                    if (c.d1.lst_obj[0][2]!=0) {
                        
                        
                        ImageView img = new ImageView(new Image("/assets/images/pokeballs/pokeball2.pnghyperball2.png"));
                        img.setFitHeight(50);
                        img.setFitWidth(50);
                        img.setPreserveRatio(true);
                        img.setVisible(true);
                        
                        Button b3 = new Button("Hyper-Ball"+" x"+c.d1.lst_obj[2][2]);
                        b3.setPrefSize(400, 90);
                        b3.setOnAction(e -> this.ShowTextAttaque("Cette fonctionalité n'est pas disponible en combat") );
                        b3.getStylesheets().add(getClass().getResource("/assets/css/objet_clickable.css").toExternalForm());

                        
                        BorderPane bp3 = new BorderPane();
                        bp3.setCenter(b3);
                        bp3.setRight(img);
                        bp3.setPrefSize(500, 100);
                        
                        vb_btn.getChildren().add(bp3);
                        break;
                        }
                    
            }
            
        }
        
        
        
        
        
        centre.setCenter(vb_btn);

        
        potlst_et_back.getChildren().addAll(marge,centre,bp_back);
        bpd.setCenter(potlst_et_back);
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void aShowPokemonList_became_useless(){
            
            
            /*
            VBox lst_poke = new VBox();
            
            
            BorderPane bp_retour = new BorderPane();
            BorderPane centre_grille = new BorderPane();
            GridPane grille_btn = new GridPane();
            
            // SET BACKGROUND 
            
            
            
            Image img_fond_bot = new Image("file:/Users/Flori/Desktop/java/Pokemon/src/pokemon/images/2.png");
            BackgroundSize bSize_bot = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
            
            Background background_bot = new Background(new BackgroundImage(img_fond_bot,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundRepeat.NO_REPEAT,
                                                                            BackgroundPosition.CENTER,
                                                                            bSize_bot));
            
            lst_poke.setBackground(background_bot);
            
            // FIN BACKGROUND
            
            
            
            // Grille de boutons
            BorderPane bp1 = new BorderPane();
            BorderPane bp2 = new BorderPane();
            BorderPane bp3 = new BorderPane();
            BorderPane bp4 = new BorderPane();
            BorderPane bp5 = new BorderPane();
            BorderPane bp6 = new BorderPane();
            
            bp1.setPrefSize(200, 75);
            bp2.setPrefSize(200, 75);
            bp3.setPrefSize(200, 75);
            bp4.setPrefSize(200, 75);
            bp5.setPrefSize(200, 75);
            bp6.setPrefSize(200, 75);
            
            
            Button poke1 = new Button("poke1");
            Button poke2 = new Button("poke2");
            Button poke3 = new Button("poke3");
            Button poke4 = new Button("poke4");
            Button poke5 = new Button("poke5");
            Button poke6 = new Button("poke6");
            
            
            
            
            poke2.setOnAction(e -> this.Switchbyko(c.d1, "1"));
            
            if (c.d1.lst_pokemon[2]!=null){
            poke3.setOnAction(e -> this.Switchbyko(c.d1, "2"));
            }
            if (c.d1.lst_pokemon[3]!=null){
            poke4.setOnAction(e -> this.Switchbyko(c.d1, "3"));
            }
            if (c.d1.lst_pokemon[4]!=null){
            poke5.setOnAction(e -> this.Switchbyko(c.d1, "4"));
            }
            if (c.d1.lst_pokemon[5]!=null){
            poke6.setOnAction(e -> this.Switchbyko(c.d1, "5"));
            }
            
            
            
            poke1.setPrefSize(190,70);
            poke2.setPrefSize(190,70);
            poke3.setPrefSize(190,70);
            poke4.setPrefSize(190,70);
            poke5.setPrefSize(190,70);
            poke6.setPrefSize(190,70);

            
            bp1.setCenter(poke1);
            bp2.setCenter(poke2);
            bp3.setCenter(poke3);
            bp4.setCenter(poke4);
            bp5.setCenter(poke5);
            bp6.setCenter(poke6);
            
            
            
            GridPane.setColumnIndex(bp1, 0);
            GridPane.setRowIndex(bp1, 0);

            GridPane.setColumnIndex(bp2, 0);
            GridPane.setRowIndex(bp2, 1);

            GridPane.setColumnIndex(bp3, 0);
            GridPane.setRowIndex(bp3, 2);
            
            GridPane.setColumnIndex(bp4, 1);
            GridPane.setRowIndex(bp4, 0);

            GridPane.setColumnIndex(bp5, 1);
            GridPane.setRowIndex(bp5, 1);

            GridPane.setColumnIndex(bp6, 1);
            GridPane.setRowIndex(bp6, 2);
            
            
            grille_btn.getChildren().addAll(bp1,bp2,bp3,bp4,bp5,bp6);
            
            
            // Bas pour le retour
            
            bp_retour.setPrefSize(500,125);
            Button btn_retour_main = new Button("retour");
            btn_retour_main.setPrefSize(300, 60);
            btn_retour_main.setOnAction(e -> ShowMainMenu());
            
            
            bp_retour.setCenter(btn_retour_main);
            
            
            Button b = new Button("e");
            
            centre_grille.setCenter(grille_btn);
            lst_poke.getChildren().addAll(centre_grille,bp_retour);
            
            bpd.setCenter(lst_poke);
            */
            
        }
    
}
