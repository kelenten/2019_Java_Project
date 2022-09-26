package lattices;

import Control.Control;
import gui.*;
import lattices.Monster.Monster;

public class Player {

    private int blood;
    private int attack;
    private int defence;
    private int yellowKey;
    private int blueKey;
    private int redKey;
    private int gold;
    private int[] position;
    private int[] nextPosition;
    private int Lv;
    private int exp;

    public Player() {
        blood = 1000;
        attack = 10;
        defence = 10;
        yellowKey = 0;
        blueKey = 0;
        redKey = 0;
        gold = 0;
        position = new int[]{0, 10, 6};
        nextPosition = new int[3];
        Lv = 1;
        exp = 0;
    }

     public int[] willMoveTo(DirectionEnum direction) {
        int[] increments = direction.getIncrements();
        nextPosition[0] = position[0] + increments[0];
        nextPosition[1] = position[1] + increments[1];
        nextPosition[2] = position[2] + increments[2];
        return nextPosition;
    }

    public void move1() {
        getStepVoice();
        position[0] = nextPosition[0];
        position[1] = nextPosition[1];
        position[2] = nextPosition[2];
        GUI.update1(this);
        Control.addTime();
        Control.save();
    }

    public void move2(){
        getStepVoice();
        position[1] = nextPosition[1];
        position[2] = nextPosition[2];
        GUI.update2(this);
        GUI.update1(this);
        Control.addTime();
        Control.save();
    }

    public void move3(int i,int j){
        getStepVoice();
        nextPosition[0] += i;
        nextPosition[1] += j;
        position[0] = nextPosition[0];
        position[1] = nextPosition[1];
        position[2] = nextPosition[2];
        GUI.update3(this);
        Control.addTime();
        Control.save();
    }

    public void move4(Monster monster){
        getStepVoice();
        position[0] = nextPosition[0];
        position[1] = nextPosition[1];
        position[2] = nextPosition[2];
        GUI.update4(this,monster);
        Control.addTime();
        Control.save();
    }

    public boolean isOnThePosition(int y,int x) {
        return position[1] == y && position[2] == x;
    }

    public boolean isNearThePosition(int y,int x){
        if(position[1] == y+1 && position[2] == x) {
            return true;
        } else if(position[1] == y-1 && position[2] == x){
            return true;
        } else if(position[1] == y && position[2] == x+1){
            return true;
        } else return position[1] == y && position[2] == x - 1;
    }

    private void getStepVoice(){
        Video.getVideo(Video.s6);
    }

    public int getPosition(int i) {
        return position[i];
    }

    public int getNextPo(int i){
        return nextPosition[i];
    }

    public void setPosition(int z,int y,int x){
        position[0] = z;
        position[1] = y;
        position[2] = x;
    }

    public void setNextPosition(int z,int y,int x){
        nextPosition[0] = z;
        nextPosition[1] = y;
        nextPosition[2] = x;
    }

    public String getAppearance() {
        return "勇";
    }

    public void reSetLv(){
        while(exp >= 75* Lv){
            exp -= 75* Lv;
            Lv++;
            blood += 100* Lv;
            attack += (3+ Lv);
            defence += (3+ Lv);
        }
    }

    public int getBlood(){
        return this.blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence(){
        return this.defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getYellowKey(){
        return this.yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey(){
        return this.blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getRedKey(){
        return this.redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }

    public int getGold(){
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLv() {
        return Lv;
    }

    public void setLv(int lv) {
        this.Lv = lv;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
