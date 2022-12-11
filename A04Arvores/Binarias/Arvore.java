//==============//
//ARVORE BINARIA//
//==============//
//ORDEM: insercao -> pesquisa -> remocao -> caminhamentos -> prints

package A04Arvores.Binarias;

public class Arvore {
    Node root;  // node raiz
    
    // inicializa a RAIZ como NULA
    public Arvore(){
        this.root = null;
    }

    //=====INSERCAO POR REFERENCIA=====//
        public void insert(int value){
            root = insert(value, root);
        }
        public Node insert(int value, Node node){
            // se NODE estiver VAZIO, ADICIONA a folha
            if (node == null) node = new Node(value);

            // senao, se o valor for MENOR do que o PAI, caminha pra ESQUERDA
            else if (value < node.value) node.left = insert(value, node.left);
            // senao, se o valor for MAIOR do que o PAI, caminha pra DIREITA
            else if (value > node.value) node.right = insert(value, node.right);

            // retorna o node inserido
            return node;
        }
    //=====INSERCAO POR REFERENCIA=====//
    //=====PESQUISA=====//
        boolean search(int x){
            return search(x, root);
        }
        boolean search(int x, Node node){
            // inicializa o resultado
            boolean result = false;

            // se o NODE estiver VAZIO, retorna FALSE
            if (node == null) result = false;
            // senão, se o PROCURADO for IGUAL o NODE, retorna TRUE
            else if (x == node.value) result = true;

            // senão, se o PROCURADO for MENOR que o NODE, caminha pra ESQUERDA
            else if (x < node.value) result = search(x, node.left);
            // senão, se o PROCURADO for MAIOR que o NODE, caminha pra DIREITA
            else if (x > node.value) result = search(x, node.right);

            // retorna o resultado da pesquisa
            return result;
        }
    //=====PESQUISA=====//
    //=====REMOCAO=====//
        void remove(int x){
            root = remove(x, root);
        }
        Node remove(int x, Node node){
            if (node != null) {
                // se o PROCURADO for MENOR do que o NODE, caminha pra ESQUERDA
                if (x < node.value) node.left = remove(x, node.left);
                // senão, se o PROCURADO for MAIOR do que o NODE, caminha pra DIREITA
                else if (x > node.value) node.right = remove(x, node.right);

                // !!! a partir daqui, o elemento foi ENCONTRADO !!! //

                // se o NODE DIREITO estiver VAZIO, "COSTURA" o NODE da ESQUERDA
                else if (node.right == null) node = node.left;
                // se o NODE ESQUERDO estiver VAZIO, "COSTURA" o NODE da DIREITA
                else if (node.left == null) node = node.right;

                // se NENHUM estiver VAZIO, APONTA pro MAIOR ELEMENTO da ESQUERDA
                else node.left = largestLeft(node, node.left);
            }
            // retorna o node removido
            return node;
        }

        Node largestLeft(Node parent, Node son){
            // se NODE DIREITO do filho estiver VAZIO
            if (son.right == null){
                // ATUALIZA o valor do PAI
                parent.value = son.value;
                // "COSTURA" o NODE ESQUERDO do filho
                son = son.left;
            }

            // senão, caminha pra DIREITA
            else son.right = largestLeft(parent, son.right);

            // retorna o FILHO
            return son;
        }
    //=====REMOCAO=====//
    //=====CAMINHAMENTOS=====//
        //-----CENTRAL-----//
        // da ESQUERDA para a DIREITA, eh ORDENDADO
        void inOrder(Node node){
            if (node != null){
                inOrder(node.left);
                System.out.print(node.value + " ");
                inOrder(node.right);
            }
        }
        void inOrder(){
            inOrder(root);
        }
            
        //-----PRE-----//
        // primeiro os NODES (cima para baixo), depois as PONTAS
        void preOrder(Node node){
            if (node != null){
                System.out.print(node.value + " ");
                preOrder(node.left);
                preOrder(node.right);
            }
        }
        void preOrder(){
            preOrder(root);
        }

        //-----POS-----//
        // primeiro as PONTAS (baixo para cima), depois os NODES
        void postOrder(Node node){
            if (node != null){
                postOrder(node.left);
                postOrder(node.right);
                System.out.print(node.value + " ");
            }
        }
        void postOrder(){
            postOrder(root);
        }
    //=====CAMINHAMENTOS=====//
    //=====PRINTS=====//
        //-----PRINTA ARVORE-----//
        public void printTree(){
            System.out.println("#--------#");
            System.out.println("| ARVORE |");
            System.out.println("#--------#");
            System.out.println("│        │");
            printTree(root, 0);
            System.out.println("#--------#");
        }

        public void printTree(Node node, int height){
            // se node for nulo, interrompe
            if (node == null) return;

            // printa o nivel e espaco
            System.out.print("│   (" + height + ")" + "  ");

            // printa o elemento
            System.out.print("├");
            for (int i = 0; i < height; i++){
                System.out.print("*─");
            }
            System.out.println(node.value);

            // printa o espacamento extra
            System.out.println("│        │");

            // CAMINHAMENTO
            printTree(node.right, height+1);
            printTree(node.left, height+1);
        }
        //-----PRINTA ARVORE-----//
        //-----PRINTA CAMINHAMENTOS-----//
        public void printInOrder(){
            System.out.println("#-----------------------#");
            System.out.print("| CAMINHAMENTO CENTRAL: |     [ " );
            inOrder();
            System.out.println("]\n#-----------------------#");
        }
        public void printPreOrder(){
            System.out.println("#-------------------#");
            System.out.print("| CAMINHAMENTO PRE: |     [ " );
            preOrder();
            System.out.println("]\n#-------------------#");
        }
        public void printPostOrder(){
            System.out.println("#-------------------#");
            System.out.print(  "| CAMINHAMENTO POS: |     [ " );
            postOrder();
            System.out.println("]\n#-------------------#");
        }
        //-----PRINTA CAMINHAMENTOS-----//
    //=====PRINTS=====//
}