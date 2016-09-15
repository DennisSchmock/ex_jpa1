/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.it2learn;

import javax.persistence.Persistence;

/**
 *
 * @author Dennis
 */
public class Main {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
    
}
