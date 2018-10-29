#include<iostream>
using namespace std;
class CRC{
	int frameSize;
	int generatorSize;
	int totalSize;
	int *frame;
	int *tempFrame;
	int *generator;
public:
	CRC(){
		cout<<" Enter frame size \n";
		cin>>frameSize;
		cout<<"Enter generator polynomial size \n";
		cin>>generatorSize;
		totalSize = frameSize+generatorSize-1;
		frame = new int[totalSize];
		tempFrame = new int[totalSize];
		generator = new int[generatorSize];
	}
	void getData();
	void calCrc();
	void check();
};
int main(){
	CRC c;
	c.getData();
	c.calCrc();
	c.check();
	return 0;
}
void CRC :: getData(){
	cout<<" Enter the frame \n";
	for(int i=0;i<frameSize;i++){
		cin>>frame[i];
		tempFrame[i] = frame[i];
	}
	// append bits to frame
	for(int i=frameSize ; i<totalSize;i++){
		frame[i]=0;
		tempFrame[i] = frame[i];
	}
	
	cout<<" Enter the Generator \n";
	for(int i=0;i<generatorSize;i++){
		cin>>generator[i];
	}
}
void CRC :: calCrc(){
	for(int i=0;i<frameSize;i++){
		if(tempFrame[i] == 0)
			continue;
		for(int j=0;j<generatorSize;j++){
			if(tempFrame[i+j] == generator[j])
				tempFrame[i+j] = 0;
			else
				tempFrame[i+j] = 1;	
		}	
	}
	cout<<" Remainder is \n";
	for(int i=frameSize ; i < totalSize ; i++){
		cout<<tempFrame[i];
		frame[i] = tempFrame[i]; // append remainder
	}
	cout<<endl;
	cout<<" Data after appending remainder \n";
	for(int i=0;i < totalSize ;i++){
		cout<<frame[i];
		tempFrame[i] = frame[i];
	}
	cout<<endl;
}
void CRC :: check(){
	//checkm on reciver's side
	// chang first bit to introduce error
	if(tempFrame[0] == 0)
		tempFrame[0] = 1;
	else
		tempFrame[0] = 0;
	
	// perform division
	for(int i=0;i < frameSize ;i++){
		if(tempFrame[i] == 0)
			continue;
		for(int j=0;j<generatorSize;j++){
			if(tempFrame[i+j] == generator[j])
				tempFrame[i+j] = 0;
			else
				tempFrame[i+j] = 1;	
		}	
	}		
	bool error = false;
	
	cout<<" Remainder is : \n";
	for(int i=frameSize;i<totalSize;i++){
		cout<<tempFrame[i];
		if(tempFrame[i] != 0)
			error = true;
	}
	cout<<endl;
	
	if(error)	
		cout<<" Non Zero remainder Error is Present  \n";
	else
		cout<<"Zero Remainder Error is not Present  \n";	
}
