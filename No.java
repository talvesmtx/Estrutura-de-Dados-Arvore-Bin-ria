  
      public class No
      {  int chave;
        No pai, esquerda, direita;
        // agora cada No sabera quem eh seu pai, seu filho da esquerda e da direita
        public No antecessor ()
        {  No n, ant;
            ant = n = esquerda;
            
            while (n != null)
            {  ant = n;
                n = n.direita;
            }
            
            return ant;
        }
        
        public No sucessor ()
        {  No n, suc;
            suc = n = direita;
            
            while (n != null)
            {  suc = n;
                n = n.esquerda;
            }
            
            return suc;
        }
        
        public int numeroFilhos()
        {  
            if(esquerda == null && direita == null)
            return 0;
            else if (esquerda != null && direita != null)
            return 2;
            else
            return 1;
        }
        
        public String toString ()
        {  
            return "<" + chave + ">";
        }
    }
    
    class ArvoreBinaria
    {  private No raiz;
        
        public ArvoreBinaria ()
        {  raiz = null;
        }
        
        public No getRaiz()
        {  return raiz;
        }
        
        public void insere (int chave)
        {  No novo = new No ();
            novo.chave = chave;
            novo.esquerda = novo.direita = null;
            
            if(raiz == null)
            {  novo.pai = null;
                raiz = novo;
            }
            else
            {  No aux = raiz, pai = null;
                
                while (aux != null)
                {  if(aux.chave == chave)
                    return; //o "chave" ja existe
                    else if (aux.chave < chave)
                    {  pai = aux;
                        aux = aux.direita; //passo a procurar aa direita
                    }
                    else
                    {  pai = aux;
                        aux = aux.esquerda; //passo a procurar aa esquerda
                    }
                }
                
                if(pai.chave > chave) pai.esquerda = novo;
                else pai.direita = novo;
                
                novo.pai = pai;
            }
        }
        
        public void remove (No n)
        {  if (raiz == null)
            throw new RuntimeException ("Lista vazia.");
            else
            {  int numFilhos = n.numeroFilhos();
                
                if(numFilhos == 0) // folha
                {  if(n==raiz) raiz = null;
                    else
                    {  if(n.pai.chave > n.chave) n.pai.esquerda = null;
                        else n.pai.direita = null;
                    }
                }
                else if(numFilhos == 1) // tem 1 filho
                {  No filho;
                    if(n.esquerda != null) filho = n.esquerda;
                    else filho = n.direita;
                    
                    if(n==raiz) raiz = filho;
                    else
                    {  if(n.pai.chave > n.chave) n.pai.esquerda = filho;
                        else n.pai.direita = filho;
                    }
                }
                else // tem 2 filhos
                {  No substituto = n.antecessor();
                    remove(substituto);
                    n.chave = substituto.chave;
                }
            }
        }
        
        public boolean contem (int chave)
        {  No aux = raiz;
            
            while (aux != null)
            {  if(aux.chave == chave)
                return true; //se encontrei, retorno true
                else if (aux.chave < chave) aux = aux.direita; //passo a procurar aa direita
                else aux = aux.esquerda; //passo a procurar aa esquerda
            }
            return false; //se chegou aqui eh porque nao existe
        }
        
        public No procurar (int chave)
        {  No aux = raiz;
            
            while (aux != null)
            {  if(aux.chave == chave)
                return aux; //se encontrei, retorno a referencia
                else if (aux.chave < chave) aux = aux.direita; //passo a procurar aa direita
                else aux = aux.esquerda; //passo a procurar aa esquerda
            }
            return null; //se chegou aqui eh porque nao existe
        }
        
        private String espacos (int n)
        {  String s="";
        for(int i=0; i<n; i++)
        s = s + "      ";
        return s;
    }
    
    public String toString ()
    {  return mostra(raiz, 1);
    }
    
    private String mostra(No n, int nivel)
    {  if(n==null) return "";
    else
    return mostra(n.direita, nivel + 1) + "\n"
    + espacos(nivel) + n.chave + "\n"
    + mostra(n.esquerda, nivel + 1);
}

public String emOrdem(No raiz)
{  if(raiz == null) return "";
else
return emOrdem(raiz.esquerda) + " "
+ raiz.chave + " " +
emOrdem(raiz.direita);
}
public String emOrdem(){
    return emOrdem(raiz);
}

/*Exerc 2*/
public int numeroDeNos (No raiz){
    if(raiz == null) return 0;
    else return numeroDeNos(raiz.esquerda) + numeroDeNos(raiz.direita) +1;
}

/*Exerc 3*/
public int somaChaves(No raiz) {

    if(raiz == null)
        return 0;
    else return raiz.chave + somaChaves(raiz.direita) + somaChaves(raiz.esquerda);
        
}
/*Exerc 4*/
public void mostraFolhas(No raiz){
    if(raiz == null) return;
    if(raiz.numeroFilhos() == 0) System.out.println(raiz.chave);
    mostraFolhas(raiz.esquerda);
    mostraFolhas(raiz.direita);
}
/* Exerc 5 */
public int alturaArvore(No raiz){
    if(raiz == null) return 0;
    int a = alturaArvore(raiz.direita);
    int b = alturaArvore(raiz.esquerda);
    if (a > b){
        return a + 1;
    }else{
        return b+1;
    }

}

/*Exerc 6 */

public No min() {
    No atual = raiz;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.esquerda;
    }
    return anterior;
  }

  public No max() {
    No atual = raiz;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.direita;
    }
    return anterior;
  }

}