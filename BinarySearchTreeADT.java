/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author emilia
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT{
    public <T extends Comparable<T>> void inserta(T elem);
    public <T extends Comparable<T>> void borra(T elem); 
    public<T extends Comparable<T>> T findMin(); 
    public <T extends Comparable<T>>T findMax(); 
    
}
