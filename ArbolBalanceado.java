/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author emilia
 */
public class ArbolBalanceado<T extends Comparable<T>> extends LinkedBinaryTree implements BinarySearchTreeADT<T> {
//no need saber el codigo, solo como insertar en dibujos 
    @Override
    public <T extends Comparable<T>> void inserta(T elem) {
       NodoBin<T> actual = this.getRaiz(); 
        NodoBin<T> papa = null;
       if(actual == null){
           raiz = new NodoBin<T>(elem);
           raiz.setPapa(null);
           cont++; 
           raiz.setFe(0);
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
        nuevo.setFe(0);
        actual = nuevo;
        boolean bandera = false; 
           while(actual.getPapa()!= null && !bandera){
               papa = actual.getPapa(); 
               if(actual.getElem().compareTo(papa.getElem())<0){
                   papa.setFe(papa.getFe()-1);
               } 
               else{
                    papa.setFe(papa.getFe()+1);
               }
               if(Math.abs(papa.getFe())== 2){
                   bandera = true; 
                   actual = rota(papa); 
               }
               else{
                   bandera = (papa.getFe()==0); 
                   actual = actual.getPapa(); 
               }
           }
    }

    @Override
    public <T extends Comparable<T>> void borra(T elem) {
       NodoBin<T> actual = busca(elem);
        if(actual == null)
            return; 
        //caso 1
        
        if(actual.getDer() == null && actual.getIzq() == null){
            if(actual == raiz){
                raiz = null; 
            } 
            else{
                if(actual.getPapa()== raiz){
                    if(raiz.getIzq()== actual)
                        raiz.setIzq(null); 
                    else
                        raiz.setDer(null); 
                    actual = raiz; 
                }
                else{
                    if(actual.getPapa().getIzq()== actual)
                        actual.getPapa().setIzq(null); 
                    else
                        actual.getPapa().setDer(null); 
                }
                
            }
        }
        else{//caso 2
            if(actual.getDer() == null || actual.getIzq() == null){
               if(actual == raiz){
                   if(actual.getDer()!= null){
                       raiz = actual.getDer(); 
                       raiz.setPapa(null);
                       actual = raiz; 
                   }
                   else{
                       raiz = actual.getIzq(); 
                       raiz.setPapa(null);
                       actual = raiz; 
                   }
                       
                }
               else{
                   if(actual.getDer()!= null){
                       if(actual ==raiz){
                        raiz = actual.getDer();
                        raiz.setPapa(null);
                        actual = raiz; 
                        }
                       else{
                           if(actual.getPapa()== raiz){
                               if(actual.getElem().compareTo(actual.getPapa().getElem())>=0){
                                    raiz.setDer(actual.getDer());
                                    raiz.getDer().setPapa(raiz);
                               }
                               else{
                                   raiz.setIzq(actual.getDer());
                                   raiz.getIzq().setPapa(raiz);
                               } 
                                  
                           }else{
                               if(actual.getElem().compareTo(actual.getPapa().getElem())>=0){
                                    actual.getPapa().setDer(actual.getDer());
                                    actual.getPapa().getDer().setPapa(raiz);
                               }
                               else{
                                   actual.getPapa().setIzq(actual.getDer());
                                   actual.getPapa().getIzq().setPapa(raiz);
                               } 
                        
                           }
                            actual = actual.getPapa();
                        
                       }
                   }
                   else{
                       if(actual.getPapa()==raiz){
                            raiz.setIzq(actual.getIzq());
                            raiz.getIzq().setPapa(raiz);
                            actual = raiz; 
                            
                        }
                       else{
                            actual.getPapa().setIzq(actual.getIzq());
                            actual.getPapa().getIzq().setPapa(actual.getPapa());
                            actual = actual.getPapa(); 
                        }
                   }
               } 
            }
            else{
                //caso 3
                NodoBin<T> sucesor = actual.getDer(); 
                
                while(sucesor.getIzq()!= null){
                    sucesor = sucesor.getIzq(); 
                }
                if(actual == raiz){
                    raiz.setElem(sucesor.getElem());
                    if(sucesor == actual.getDer()){
                        raiz.setDer(sucesor.getDer()); 
                    }
                    else{
                        if(sucesor.getDer() == null){
                            sucesor.getPapa().setIzq(null); 
                        }
                        else{
                            sucesor.getPapa().setDer(sucesor.getDer());
                        }
                    }
                }
                else{
                    actual.setElem(sucesor.getElem());
                    if(sucesor == actual.getDer()){
                        actual.setDer(sucesor.getDer()); 
                    }
                    else{
                        if(sucesor.getDer() == null){
                            sucesor.getPapa().setIzq(null); 
                        }
                        else{
                            sucesor.getPapa().setDer(sucesor.getDer());
                        }
                    }
                    
                } 
                actual = sucesor.getPapa(); 
            }
       }
        cont--; 
        
        boolean bandera = true; 
        //nueva parte; 
       if(actual == raiz){
           //System.out.println("\n altura der: "+ this.altura(raiz.getDer()));
           //System.out.println("altura izq: "+ this.altura(raiz.getIzq()));
            raiz.setFe(this.altura(raiz.getDer()) - this.altura(raiz.getIzq()));
            if(Math.abs(raiz.getFe())== 2){
                rota(raiz); 
            }
            //System.out.println("raiz fe "+ raiz.getFe());
        }
           while(actual!= raiz  && bandera){
               
               NodoBin<T> papa = actual.getPapa(); 
               if(actual.getElem().compareTo(papa.getElem())<0){
                   papa.setFe(papa.getFe()+1);
               } 
               else
                   papa.setFe(papa.getFe()-1);
               if(Math.abs(papa.getFe())== 2){
                   actual = rota(papa); 
               }
               else{
                   if(Math.abs(papa.getFe())== 1)
                        bandera = false; 
               }
               actual = actual.getPapa(); 
           }
        
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
    public <T extends Comparable<T>> T findMax() {
        NodoBin<T> actual = raiz; 
        while(actual.getDer()!= null){
            actual = actual.getDer(); 
        }
        return actual.getElem(); 
    }
    
    private <T extends Comparable<T>> NodoBin<T> rota(NodoBin<T> n) {
        //izquierda izquierda
        if(n.getFe() ==-2 && n.getIzq().getFe()==-1){
            NodoBin<T> papa = n.getPapa(); 
            NodoBin<T> alfa = n; 
            NodoBin<T>beta = n.getIzq(); 
            NodoBin<T> d= n.getDer(); 
            NodoBin<T> gamma = beta.getIzq(); 
            NodoBin<T> c = beta.getDer();
            NodoBin<T> a = gamma.getIzq();
            NodoBin<T> b = gamma.getDer();
            gamma.cuelga2(a, 'i');
            gamma.cuelga2(b, 'd');
            beta.cuelga2(gamma, 'i');
            beta.cuelga2(alfa, 'd');
            alfa.cuelga2(d, 'd');
            alfa.cuelga2(c, 'i'); 
            if(papa == null){
                raiz = beta; 
                raiz.setPapa(null);
            }
                
            else
                papa.cuelga(beta);
            gamma.setFe(this.altura(b)-this.altura(a));
            alfa.setFe(this.altura(d)- this.altura(c));
            beta.setFe(this.altura(alfa)-this.altura(gamma));
            return beta; 
        }
        //derecha derecha
        if(n.getFe() ==2 && n.getDer().getFe()==1){
            NodoBin<T> papa = n.getPapa(); 
            NodoBin<T> alfa = n; 
            NodoBin<T>beta = n.getDer(); 
            NodoBin<T> a = n.getIzq(); 
            NodoBin<T> gamma = beta.getDer(); 
            NodoBin<T> b = beta.getIzq();
            NodoBin<T> c = gamma.getIzq();
            NodoBin<T> d= gamma.getDer();
            gamma.cuelga2(c, 'i');
            gamma.cuelga2(d, 'd');
            alfa.cuelga2(b, 'd');
            alfa.cuelga2(a, 'i'); 
            beta.cuelga2(alfa, 'i');
            beta.cuelga2(gamma, 'd');
            if(papa == null){
                raiz = beta; 
                raiz.setPapa(null);
            }
            else
                papa.cuelga(beta);
            gamma.setFe(this.altura(d)-this.altura(c));
            alfa.setFe(this.altura(b)- this.altura(a));
            beta.setFe(this.altura(gamma)-this.altura(alfa));
            return beta; 
        }
        //izquierda derecha
        if(n.getFe() ==-2 && n.getIzq().getFe()==1){
            NodoBin<T> papa = n.getPapa(); 
            NodoBin<T> alfa = n; 
            NodoBin<T>beta = n.getIzq(); 
            NodoBin<T> d = n.getDer(); 
            NodoBin<T> gamma = beta.getDer(); 
            NodoBin<T> a = beta.getIzq();
            NodoBin<T> b = gamma.getIzq();
            NodoBin<T> c= gamma.getDer();
            alfa.cuelga2(c, 'i');
            alfa.cuelga2(d, 'd');
            beta.cuelga2(a, 'i');
            beta.cuelga2(b, 'd');
            gamma.cuelga2(alfa, 'd');
            gamma.cuelga2(beta, 'i'); 
            if(papa == null){
                 raiz = gamma; 
                 raiz.setPapa(null);
            }
               
            else
                papa.cuelga(gamma);
            alfa.setFe(this.altura(d)-this.altura(c));
            beta.setFe(this.altura(b)- this.altura(a));
            gamma.setFe(this.altura(alfa)-this.altura(beta));
            return gamma; 
        }
        if(n.getFe() ==2 && n.getDer().getFe()==-1){
            NodoBin<T> papa = n.getPapa(); 
            NodoBin<T> alfa = n; 
            NodoBin<T>beta = n.getDer(); 
            NodoBin<T> a = n.getIzq(); 
            NodoBin<T> gamma = beta.getIzq(); 
            NodoBin<T> d = beta.getDer();
            NodoBin<T> b = gamma.getIzq();
            NodoBin<T> c= gamma.getDer();
            beta.cuelga2(c, 'i');
            beta.cuelga2(d, 'd');
            alfa.cuelga2(a, 'i');
            alfa.cuelga2(b, 'd');
            gamma.cuelga2(beta, 'd');
            gamma.cuelga2(alfa, 'i'); 
            if(papa == null){
                raiz = gamma; 
                raiz.setPapa(null);
            }
                
            else
                papa.cuelga(gamma);
            beta.setFe(this.altura(d)-this.altura(c));
            alfa.setFe(this.altura(b)- this.altura(a));
            gamma.setFe(this.altura(beta)-this.altura(alfa));
            return gamma; 
        }
       
        return raiz; 
    }
    public <T extends Comparable <T>> NodoBin<T> busca(T elem){
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
        if(encontrar != true)
            return null;
        else
            return actual; 
    }
    public <T extends Comparable<T>> int altura(NodoBin<T> n){
        if(n==null)
            return 0; 
        if(n.getDer() == null && n.getIzq() == null)
            return 1; 
        return alt(n, 0); 
    }

    private<T extends Comparable<T>> int alt(NodoBin<T> actual, int cont) {
        if(actual == null)
            return cont; 
        int m1 = alt(actual.getIzq(), cont+1); 
        int m2 = alt(actual.getDer(),cont+1); 
        return Math.max(m1, m2); 
    }
}
