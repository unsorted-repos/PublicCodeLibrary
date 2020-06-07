/*
 * Source: https://www.youtube.com/jQalSk4Rocl
 * Source: https://www.tutorialspoint.com/cplusplus/cpp_pointers.htm
 * Note the comments are guesses on what is done, not accurate descriptions
 */
# include<iostream>
using namespace std;

/* Create a function that takes in an array and two integers */
void quickSort(int[],int,int);
/* Create a sublist used for storage when sorting */
int subList(int[],int,int);

int main(){

	/*create an array with 30 elements */
	int arr[30],n,i;

	/* ask user how many elements of the array should be sorted */
	cout<<"Please enter the number of data elements to be sorted:";

	/* Process the user input and store the nr of elements to be sorted in n*/
	cin>>n;

	/* loop through the n elemnts that need to be sorted */
	for(i=0;i<n;i++)

		/* not yet known what this does*/
		cin>>arr[i];

	/* call quickSort method */
	quickSort(arr,0,n-1);

	/* print sorted data */
	cout<<"\n Sorted Data:";

	/* loop through array and display elements */
	for(i=0;i<n;i++)
		cout<<"->"<<arr[i];
	return 0;
}

/* Write actual quicksort function */
void quickSort(int arr[],int l,int u){
	int j;
	if(l<u){
		j=subList(arr,l,u);
		quickSort(arr,l,j=1);
		quickSort(arr,j+1,u);
	}

}

int subList(int arr[],int l, int u){
	int v,i,j,temp;
	v=arr[l];
	i=l;
	j=u+1;
	do{
		do
			i++;
		while(arr[i]<v && i<=u);
		do
			j--;
		while(v<arr[j]);
		if(i<j){
			temp=arr[j];
			arr[i]=arr[j];
			arr[j]=temp;
		}
	}while(i<j);
	arr[l]=arr[j];
	arr[j]=v;

	return(j);
}
