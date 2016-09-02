public interface GamePanel {
    public void showRedButtonBox( );
    public void clearRedButtonBox( );
    public void showGreenButtonBox( );
    public void clearGreenButtonBox( );

    public void setLabelText(String str);
    public void clearLabelText( );

    public void setLedRed(int led);
    public void setLedYellow(int led);
    public void setLedGreen(int led);
    public void setLedBlack(int led);
    public void setAllLedsBlack( );
}
