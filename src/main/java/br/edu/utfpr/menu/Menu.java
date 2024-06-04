/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.menu;
import java.util.Scanner;
/**
 *
 * @author Garra pc
 */
public abstract class Menu {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void mostrarMenu();
}