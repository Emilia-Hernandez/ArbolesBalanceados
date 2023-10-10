/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author emilia
 */
public class ArbolesBinariosDeBusqueda<T> extends LinkedBinaryTree implements BinarySearchTreeADT<T>{
    
    @Override
    public <T extends Comparable<T>> void inserta(T elem) {
        NodoBin<T> actual = this.getRaiz(); 
        NodoBin<T> papa = null;
       if(actual == null){
           raiz = new NodoBin<T>(elem);
           raiz.setPapa(null);
           cont++; 
           return; 
       }
       while(actual != null){
           papa = actual; 
           if(actual.getElem().compareTo(elem)<=0)
               actual = actual.getDer(); 
           else
               actual = actual.getIzq(); 
       } 
       NodoBin<T> nuevo = new NodoBin<T>(elem);
       if(elem.compareTo(papa.getElem())<=0)
          papa.setIzq(nuevo); 
       else
          papa.setDer(nuevo);
        cont++; 
        nuevo.setPapa(papa);
    }
    private <T extends Comparable <T>> void insertaRec(NodoBin<T> actual, NodoBin<T> nuevo){
        if (actual == null) {
            raiz = nuevo; 
            return; 
        }
        if(nuevo.compareTo(actual)<=0){
            if(actual.getIzq() == null)
                actual.cuelga(nuevo);
            else
                insertaRec(actual.getIzq(), nuevo); 
        }
        else{
            if(actual.getDer() == null)
                actual.cuelga(nuevo);
            else
                insertaRec(actual.getDer(), nuevo); 
        }
    }

    @Override
    public <T extends Comparable <T>> void borra(T elem) {
        NodoBin<T> actual = busca(elem); 
        if(actual == null)
            return; 
        //caso 1
        if(actual.getDer() == null && actual.getIzq() == null){
            if(actual == raiz){
                raiz = null; 
            } 
            else{
                actual.getPapa().setDer(null);
                actual.getPapa().setIzq(null);
            }
            cont--;
            return;
        }
        //caso 2
        if(actual.getDer() == null || actual.getIzq() == null){
               if(actual == raiz){
                   if(actual.getDer()!= null){
                       raiz = actual.getDer(); 
                       raiz.setPapa(null);
                   }
                   else{
                       raiz = actual.getIzq(); 
                       raiz.setPapa(null);
                   }
                       
                }
               else{
                   if(actual.getDer()!= null){
                       if(actual.getPapa().getElem().equals(raiz.getElem())){
                        raiz.setDer(actual.getDer());
                        actual.setPapa((null));
                        }
                       else{
                        actual.getPapa().setDer(actual.getDer());
                         actual.setPapa((null));
                       }
                   }
                   else{
                       if(actual.getPapa().getElem().equals(raiz.getElem())){
                        raiz.setDer(actual.getIzq());
                        actual.setPapa((null));
                        }
                       else{
                         actual.getPapa().setIzq(actual.getIzq());
                         actual.setPapa((null));
                       }
                   }
               }
               cont--; 
               return; 
       }
       //caso 3
       
       NodoBin<T> sucesor = actual.getDer(); 
       while(sucesor.getIzq()!= null){
           sucesor = sucesor.getIzq(); 
       }
       if(actual == raiz){
           raiz.setElem(sucesor.getElem());
           if(sucesor.getDer() == null){
               sucesor.getPapa().setIzq(null); 
           }
           else{
              sucesor.getPapa().setDer(sucesor.getDer());
           }
           cont++; 
           return; 
       }
       actual.setElem(sucesor.getElem());
       if(sucesor.getDer() == null){
           sucesor.getPapa().setIzq(null); 
       }
       else{
           sucesor.getPapa().setDer(sucesor.getDer());
       }
       cont--; 
       return; 
    }

    @Override
    public <T extends Comparable<T>> T findMin() {
        NodoBin<T> actual = raiz; 
        while(actual.getIzq()!= null){
            actual = actual.getIzq(); 
        }
        return actual.getElem(); 
    }

    @Override
    public <T extends Comparable <T>> T findMax() {
       NodoBin<T> actual = raiz; 
        while(actual.getDer()!= null){
            actual = actual.getDer(); 
        }
        return actual.getElem(); 
    }
    private <T extends Comparable <T>> NodoBin<T> busca(T elem){
        NodoBin<T> actual = raiz; 
        boolean encontrar = false; 
        while(actual != null && !encontrar){
            if(actual.getElem() == elem)
                encontrar = true; 
            else{
                 if(actual.getElem().compareTo(elem)>0)
                actual = actual.getIzq(); 
            else
                actual = actual.getDer(); 
            }
        }
        if(encontrar = false)
            return null;
        else
            return actual; 
    }
}
