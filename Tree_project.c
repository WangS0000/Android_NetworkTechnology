#include<stdio.h>
#include<stdlib.h>
typedef char DataType;
//创建二叉树类型
typedef struct Node{
	DataType data;
	struct Node *lchild;
	struct Node *rchild;
}BiTNode,*BiTree;//其中，BiTree为二叉树的指针，与lchild，rchild为同一类型
//声明方法
BiTree createBiTree(BiTree root);
void PostOrder(BiTree root);
void PreOrder(BiTree root);
void InOrder(BiTree root);
char str[] ="ABC^D^^E^^FGH^^^I^J^^";//二叉树的原始值，其中,^为右线，无则为左线
BiTree createBiTree(BiTree root){//创建二叉树
	static int count;//这里加静态的原因是:static静态表共有，在创建二叉树的递归方法中，需要count在每一层递归中依次叠加，不需要每次创新int值，count默认为0
	char ch = str[count];
	count++;
	if(ch=='^'){
		return NULL;//该方法结束，返回上一层方法
	}
	root = (BiTNode*)malloc(sizeof(BiTNode));
	root->data = ch ;
	root->lchild = createBiTree(root->lchild);
	root->rchild = createBiTree(root->rchild);
	return root;
}
void PreOrder(BiTree root){//先序遍历:根->左->右
	if(root==NULL){
		return;
	}
	printf("%c",root->data);
	PreOrder(root->lchild);
	PreOrder(root->rchild);
}
void InOrder(BiTree root){//中序遍历:左->根->右
	if(root==NULL){
		return;
	}
	InOrder(root->lchild);
	printf("%c",root->data);
	InOrder(root->rchild);
}
void PostOrder(BiTree root){//后序遍历:左->右->根
	if(root==NULL){
		return;
	}
	PostOrder(root->lchild);
	PostOrder(root->rchild);
	printf("%c",root->data);
}
int main(){
	BiTree root;
	root = createBiTree(root);
	printf("先序遍历:");
	PreOrder(root);
	printf("\n中序遍历:");
	InOrder(root);
	printf("\n后序遍历:");
	PostOrder(root);
	printf("\n");
}