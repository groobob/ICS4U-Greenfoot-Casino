import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CasinoWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CasinoWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 740, 1, false);

        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        SlotMachines slotMachines = new SlotMachines();
        addObject(slotMachines,94,294);
        SlotMachines slotMachines2 = new SlotMachines();
        addObject(slotMachines2,164,292);
        slotMachines2.setLocation(168,299);
        SlotMachines slotMachines3 = new SlotMachines();
        addObject(slotMachines3,252,293);
        SlotMachines slotMachines4 = new SlotMachines();
        addObject(slotMachines4,332,293);
        SlotMachines slotMachines5 = new SlotMachines();
        addObject(slotMachines5,412,295);
        SlotMachines slotMachines6 = new SlotMachines();
        addObject(slotMachines6,93,361);
        SlotMachines slotMachines7 = new SlotMachines();
        addObject(slotMachines7,163,359);
        SlotMachines slotMachines8 = new SlotMachines();
        addObject(slotMachines8,248,363);
        slotMachines3.setLocation(236,315);
        slotMachines8.setLocation(229,366);
        slotMachines3.setLocation(230,317);
        slotMachines8.setLocation(236,348);
        slotMachines4.setLocation(314,300);
        slotMachines5.setLocation(308,371);
        slotMachines5.setLocation(307,367);
        SlotMachines slotMachines9 = new SlotMachines();
        addObject(slotMachines9,385,298);
        SlotMachines slotMachines10 = new SlotMachines();
        addObject(slotMachines10,386,359);
        slotMachines.setLocation(69,180);
        slotMachines2.setLocation(172,181);
        slotMachines3.setLocation(233,172);
        slotMachines4.setLocation(281,182);
        slotMachines9.setLocation(348,168);
        slotMachines4.setLocation(295,178);
        slotMachines.setLocation(110,180);
        slotMachines9.setLocation(348,204);
        slotMachines5.setLocation(306,361);
        slotMachines8.setLocation(222,356);
        slotMachines3.setLocation(231,190);
        slotMachines4.setLocation(298,190);
        slotMachines9.setLocation(362,198);
        slotMachines5.setLocation(278,368);
        slotMachines10.setLocation(357,362);
        slotMachines3.setLocation(251,179);

        horsebettingplaceholder horsebettingplaceholder = new horsebettingplaceholder();
        addObject(horsebettingplaceholder,762,230);
        horsebettingplaceholder.setLocation(716,112);
        horsebettingplaceholder.setLocation(693,29);
        horsebettingplaceholder horsebettingplaceholder2 = new horsebettingplaceholder();
        addObject(horsebettingplaceholder2,726,27);
        horsebettingplaceholder horsebettingplaceholder3 = new horsebettingplaceholder();
        addObject(horsebettingplaceholder3,773,33);
        horsebettingplaceholder3.setLocation(771,32);
        horsebettingplaceholder3.setLocation(762,27);
        horsebettingplaceholder horsebettingplaceholder4 = new horsebettingplaceholder();
        addObject(horsebettingplaceholder4,800,32);
        horsebettingplaceholder4.setLocation(798,32);
        slotMachines3.setLocation(212,191);
        slotMachines4.setLocation(284,192);
        slotMachines9.setLocation(348,190);
        slotMachines5.setLocation(291,363);
        Ordinary ordinary = new Ordinary();
        addObject(ordinary,717,355);
    }
}
