package tools;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TF {
	//����һ���ļ���ÿ���ʵ��ĵ�Ƶ��
	public static HashMap<String,Double> getTF (String filename){
		//���ڴ洢�ļ��е���Ӵ���ı�
		ArrayList<String>lines=new ArrayList<String>();
		//���ڴ洢ÿ�����ʵĴ�Ƶ
		HashMap<String,Integer> wordMap=new HashMap<String,Integer>();
		//���ڴ洢ÿ�����ʵ�tf
		HashMap<String,Double> tfMap=new HashMap<String,Double>();
		//���е�������
		int totalCount=0;
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);
			//���ļ�����ȫ�����뵽�ɱ������
			String line=null;
			while((line=br.readLine())!=null) {
				lines.add(line);
			}
			//�ر��ļ�����
			br.close();
			reader.close();
			//����ÿ�����ʵĴ�Ƶ
			for(int i=0;i<lines.size();i++) {
				String []strArray=lines.get(i).split(" |��|��|��|��|��|��|,|\\.|;|\\?|!|\"|\\'|\\-|\\(|\\)|]");
				totalCount+=strArray.length;
				for(int j=0;j<strArray.length;j++) {
					if(strArray[i]==" "||strArray[i]==""||strArray[i]==null)continue;
					if(wordMap.containsKey(strArray[j])) {
						wordMap.put(strArray[j], wordMap.get(strArray[j])+1);
					}
					else {
						wordMap.put(strArray[j], 1);
					}
				}
			}
			//����ÿ�����ʵ�tf
			Iterator<String> it=wordMap.keySet().iterator();
			while(it.hasNext()) {
				String key=it.next();
				tfMap.put(key,1.0*wordMap.get(key)/totalCount);
			}
		}
		//�쳣����
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return tfMap;
	}
}
