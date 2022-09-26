package Control;

import gui.GUI;
import javafx.application.Application;
import lattices.Lattice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Control {

    private static int time;
    private static List<Integer> save=new ArrayList<Integer>();

    public static void main(String[] args){
        Application.launch(GUI.class, args);
    }

    public static void save(){
        save.add(GUI.getPlayer().getBlood());
        save.add(GUI.getPlayer().getAttack());
        save.add(GUI.getPlayer().getDefence());
        save.add(GUI.getPlayer().getGold());
        save.add(GUI.getPlayer().getYellowKey());
        save.add(GUI.getPlayer().getBlueKey());
        save.add(GUI.getPlayer().getRedKey());
        save.add(GUI.getPlayer().getPosition(0));
        save.add(GUI.getPlayer().getPosition(1));
        save.add(GUI.getPlayer().getPosition(2));
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 13;j++){
                for(int k = 0;k < 13;k++){
                    Lattice lattice = GUI.getMap().getLattices(i , j , k);
                    if(lattice.getExist()){
                        save.add(1);
                    } else save.add(0);
                }
            }
        }
    }

    public static void readSave() throws FileNotFoundException {
        System.out.println("读取存档");
        File file1 = new File("Player.txt");
        File file2 = new File("map.txt");
        Scanner input1 = new Scanner(file1);
        Scanner input2 = new Scanner(file2);
        GUI.getPlayer().setBlood(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setAttack(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setDefence(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setGold(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setYellowKey(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setBlueKey(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setRedKey(Integer.parseInt(input1.nextLine()));
        GUI.getPlayer().setPosition(Integer.parseInt(input1.nextLine()),Integer.parseInt(input1.nextLine()),Integer.parseInt(input1.nextLine()));
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 13;j++){
                for(int k = 0;k < 13;k++){
                    Lattice lattice = GUI.getMap().getLattices(i , j , k);
                    if(input2.nextInt() == 1){
                        lattice.setExist(true);
                    } else lattice.setExist(false);
                }
            }
        }


    }

    public static void record() throws FileNotFoundException {
        System.out.println("存档");
        File file1 = new File("Player.txt");
        File file2 = new File("map.txt");
        PrintWriter output1 = new PrintWriter(file1);
        PrintWriter output2 = new PrintWriter(file2);
        output1.println(GUI.getPlayer().getBlood());
        output1.println(GUI.getPlayer().getAttack());
        output1.println(GUI.getPlayer().getDefence());
        output1.println(GUI.getPlayer().getGold());
        output1.println(GUI.getPlayer().getYellowKey());
        output1.println(GUI.getPlayer().getBlueKey());
        output1.println(GUI.getPlayer().getRedKey());
        output1.println(GUI.getPlayer().getPosition(0));
        output1.println(GUI.getPlayer().getPosition(1));
        output1.println(GUI.getPlayer().getPosition(2));
        output1.close();
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 13;j++){
                for(int k = 0;k < 13;k++){
                    Lattice lattice = GUI.getMap().getLattices(i , j , k);
                    if(lattice.getExist()){
                        output2.print(1 + " ");
                    } else output2.print(0 + " ");
                }
                output2.println("");
            }
        }
        output2.close();
    }

    public static void undo(){
        if(time > 0){
            time--;
            GUI.getPlayer().setBlood(save.get(time*855));
            GUI.getPlayer().setAttack(save.get(time*855+1));
            GUI.getPlayer().setDefence(save.get(time*855+2));
            GUI.getPlayer().setGold(save.get(time*855+3));
            GUI.getPlayer().setYellowKey(save.get(time*855+4));
            GUI.getPlayer().setBlueKey(save.get(time*855+5));
            GUI.getPlayer().setRedKey(save.get(time*855+6));
            GUI.getPlayer().setPosition(save.get(time*855+7),save.get(time*855+8),save.get(time*855+9));
            for(int i = 0;i < 5;i++){
                for(int j = 0;j < 13;j++){
                    for(int k = 0;k < 13;k++){
                        Lattice lattice = GUI.getMap().getLattices(i , j , k);
                        if(save.get(time*855+10+(i*169+j*13+k)) == 1){
                            lattice.setExist(true);
                        } else lattice.setExist(false);
                    }
                }
            }

            for(int i = 0;i < (save.size()-(time+1)*855);i++){
                for(int j = 0;j < 855;j++)
                save.remove((time+1)*855);
            }
        } else {
            System.out.println("无法再撤销！");
        }

    }

    public static void addTime(){
        time++;
    }

    public static void clearSave(){
        save.clear();
        time = 0;
    }
}
