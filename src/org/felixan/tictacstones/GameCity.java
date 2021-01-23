package org.felixan.tictacstones;

import java.awt.Color;

import becker.robots.City;
import becker.robots.Intersection;
import becker.robots.icons.IntersectionIcon;

public class GameCity extends City {

    public GameCity(int s, int a) {
        super(s, a);
    }

    // modifier used to change the color of the roads and intersections
    protected void customizeIntersection(Intersection intersection) {
        IntersectionIcon icon = new IntersectionIcon(Color.white, Color.pink);
        intersection.setIcon(icon);
    }
}
