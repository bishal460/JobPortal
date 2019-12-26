package GUI;

import javax.swing.*;
import java.awt.*;

public class AddJobPanel extends JPanel {
    AddPanelInterface add;


    public AddJobPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame add) {

        super(layout, isDoubleBuffered);


        this.add = add;
    }

    interface AddPanelInterface{
        void onJobAdd();
    }
}
