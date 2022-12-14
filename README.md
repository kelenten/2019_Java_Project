# Project 2: 魔塔

## 项目目标

在 Project 1 中，同学们实现了一个命令行界面的魔塔小游戏。Project 2 延续 Project1 的内容，通过 GUI 扩展和部分进阶功能，使我们这个游戏更具娱乐性和可玩性。

我们将 Project2 中需要实现的内容分为逻辑和界面。在 Project 1 中，同学们基本上已经完成了大部分的逻辑操作，期末pj相比期中pj，战斗逻辑有所升级，除此之外大家应将重点放在重构代码和使用面向对象设计的部分。图形化界面素材在 `audio` 和 `pic` 文件夹中给出。大家也可以自由发挥，打造属于你自己的魔塔，只要展示清晰、明了即可。

## 基本要求

### 重构代码

大部分同学的 PJ1 代码可以通过重构来提高代码质量。

PJ2 对代码风格有以下三点具体要求：

1. 单个方法的长度不应超过 300 行（如有特殊情况可向助教提出）。
2. 方法名和变量名命名得当。
3. 方法和类的作用与意义清晰明确。
4. 逻辑和界面分开设计，不要把他们混着写在一个很大的类里

### 面向对象设计

使用面向对象的思想来设计代码结构是 PJ2 的重点要求。在同学们动手写 PJ2 代码之前，助教建议同学们根据所学知识，设计好 PJ2 基础功能的每个类。

推荐同学们将自己设计的 **逻辑部分** 中每个类以文档的方式记录下来。内容包括：

1. 类名、类的意义和作用。
2. 类的父类、子类和接口，以及这么设计的理由。（如果有）
3. 类的构造函数、可见域和可见方法以及它们的作用。（最主要的一点，体现封装的概念）
4. 类的私有域及其作用。（可以只列出关键的几个）
5. 类的私有方法。（不一定需要）
6. 其他有必要提及的内容，比如和其他某个类之间的联系。

### 图形化界面

图形化界面是 PJ2 相比 PJ1 最明显的升级，也是 PJ2 中最有趣的部分。

图形化界面部分自由度较高，同学们可以在此充分发挥自己的想象力和创造力。

#### 游戏地图

下面是游戏的示例界面，大家可以参考，不必仿照。
![](https://github.com/Java-A-2019/project2/blob/master/demo.png?raw=true)

#### 战斗逻辑升级

在pj2中每个怪物都获得了一些新能力

史莱姆家族们获得了不同程度得暴击率和闪避率（暴击伤害为原伤害（攻击力-防御力）的两倍，闪避为躲避此次伤害）

绿色史莱姆 20%暴击率 20%闪避率

红色史莱姆 25%暴击率 25%闪避率

黑色史莱姆 33%暴击率 33%闪避率

蝙蝠们获得了毒液效果，首次攻击玩家会让玩家中毒，玩家处于中毒状态每回合开始时会百分比掉血（向上取整），且被攻击会受到额外伤害。

小蝙蝠  每回合开始时损失1%当前血量  被攻击额外受到5点伤害

大蝙蝠  每回合开始时损失2%当前血量  被攻击额外受到10点伤害

骷髅家族们每次攻击有概率造成特殊攻击，特殊攻击无视玩家20%的防御力（向上取整）并且造成120%攻击力的伤害（实际计算还需减去玩家的防御力，向上取整）

骷髅人    20%

骷髅士兵  25%

骷髅队长  33%

石头人的攻击和防御能力得到了加强，每受到一次攻击，自己防御力和攻击力永久+2

#### 升级版怪物手册

在pj2中怪物手册除了显示pj1怪物手册内容（怪物名称，攻击力，防御力，生命值，金钱）外，需模拟一次战斗显示损血且需显示模拟战斗的回合数。另外对于不同种怪物，需在备注一栏标明一些相关信息。

对于史莱姆家族需显示暴击次数和闪避次数。

对于蝙蝠们需显示百分比中毒效果一共让玩家损失血量和额外受伤一共让玩家损失的血量

对于骷髅人需显示特殊攻击的次数

对于石头人需显示 战斗结束时石头人的攻击力和防御力

#### 玩家移动

玩家移动需同时支持键盘控制和鼠标控制，键盘即用上下左右控制玩家移动，鼠标移动即点击一个目标块即可移动过去。

#### 帮助、重新开始、退出功能

这三个功能要求实现，具体实现方式由同学们自己定义，合理即可。

同学们可以创建菜单栏，将这些功能放入菜单中，也可以设置几个按钮，放在合适的地方。

#### 游戏胜利

由于攻击方式的改变，玩家可能死亡，在玩家生命值小于等于0时，需提示玩家游戏失败，重新开始。

胜利提示或胜利界面由同学们自己定义，合理即可。

#### 悔棋、取消悔棋

考虑到游戏的正常逻辑，取消悔棋功能可以不在图形化界面中提供，但悔棋功能必须实现。

#### 存档

同pj1

## 拓展功能

PJ2 要求同学们实现三个拓展功能。拓展功能没有硬性要求，同学们可以自由发挥，但注意不要跑偏太远。

拓展功能部分共 15 分。每个拓展功能占 5 分。大家如果想完成本文档外的拓展功能，最好提前问一下助教。

### 可选的拓展功能

#### 音效

助教给的资源中包括了音效，尝试将其应用到游戏中吧~
（开门，捡到不同东西，击败敌人包括走路都可以有不同的音效）

#### 动画系统

玩家移动不仅仅是单纯的从一个格子变到另一个格子，而是以动画的效果呈现。战斗时候也可以有一些其他的动画效果，有待同学们自行发掘。

#### 好看的界面

自由发挥~ 展现你的设计与创意~

注意：纯粹的美工与设计原则上不能取得大于 4 分的成绩，需要有代码上的难度才能取得最高的分数，比如结合代码和美术素材实现动画效果等。

#### 完善的商人和等级系统

在pj中的商人是不完整的。完整版的商人系统应该可以选择性购买血量，防御力，攻击力以及各类钥匙。同时购买过的商品下次再进行购买费用要翻倍。

同时击败怪物除了获得金钱外，还应该获得经验上的提升，当经验满到一定程度，可以升级。获得攻击力，防御力的提升以及血量的恢复。


#### 更具可玩性的游戏设计

一个好玩的游戏需要多方面的努力，比如有意思的剧情，好玩的游戏逻辑，精美的动画与人物场景，好听的音乐和音效。魔塔这种固定数值的rpg游戏还需要很好的数值控制。

大家可以自行探索如何让自己写出的pj更有可玩性。

#### Android版或者web版魔塔

这部分适用于想要参加比赛，或者对于安卓，web感兴趣的同学。

#### AI 功能

对Artificial Intelligence感兴趣的同学可以自行研究。

#### 使用unity或其他游戏引擎开发

学习成本相当高，有时间且技术水平高的同学谨慎尝试。

#### 联机对战功能

具体联机方法还需大家自己尝试。


## 评分标准

1. 代码风格：10 分
2. 面向对象设计：20 分
3. 图形化界面：40 分
4. 拓展功能：15 分
5. 设计文档：7 分
6. 用户手册：4 分
7. 面试情况：4 分

## 提交与面试

### 提交截止时间

本次课程项目提交截止时间为 **2019 年 12 月 25 日 23:59**。

建议同学们在截止时间前一周就将项目基本完成，以防来不及完成，或者来不及修正突然发现的 bug 。

### 提交方式

请提交源代码、文档。源代码应以项目的形式提交。如有必要可以提交其他材料。

在截止时间之前将提交材料压缩并上传到：

```
ftp://10.142.141.33/classes/19/191 程序设计(陈荣华)/WORK_UPLOAD/PJ2
```

压缩包请重命名为：`学号 + 姓名`。 如 `19302010002丁昊.rar`，`19302010003范舒扬.zip`。

如果发现之前提交的文件有问题，可以重新上传压缩包。ftp不允许删除或者覆盖文件，需要上传一个新的压缩包，命名格式为：`学号 + 姓名 + 次数`， 如`19302010003范舒扬2.zip`。

### 迟交惩罚

每迟交一天，最终得分扣除20%。

> 注：如果提交多次，以面试时选择的提交文件的提交时间为准。评分亦以此文件为准。

### 面试注意事项

本次课程项目面试时间为**2020 年 1 月 4 日 10：00**，地点为机房。如有调整会提前通知。 

原则上面试时不允许现场Debug，请确保你的程序能正常运行。

### 抄袭惩罚

**严禁任何形式的抄袭。**

助教将检查每个同学的代码、文档等材料，如有发现抄袭现象，将严肃处理。

抄袭同学零分处理。被抄袭同学将视情况作出惩罚。