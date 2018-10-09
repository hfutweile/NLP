package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class IDF {
	//ѵ���ļ�������
	public static int TOTALDOC=0;
	//�������дʵ������ĵ�Ƶ��
	public static HashMap<String,Double> getIDF(String dir){
		//���ص����дʵ����ĵ�Ƶ��
		HashMap<String,Double>idfMap=new HashMap<String,Double>();
		
		//���������ļ����������ļ�
		File file=new File(dir);
		File[]files=file.listFiles();
		TOTALDOC=files.length;
		
		//����ÿ���ļ�������ÿ���ļ��еĴ��з֡�Ψһ���������˳��ֵ��ĵ���������idfMap��
		for(File f:files) {
			HashSet<String> fileWord=new HashSet<String>();
			String filename=f.getAbsolutePath();
			try {
				FileReader reader = new FileReader(filename);
				BufferedReader br = new BufferedReader(reader);
				String line=null;
				while((line=br.readLine())!=null) {
					//��ȡ�ļ����ݲ����뵽hashset�У���ȡ���ļ����е���
					String []strArr=line.split(" |��|��|��|��|��|��|,|\\.|;|\\?|!|\"|\\'|\\-|\\(|\\)|]");
					for(int i=0;i<strArr.length;i++) {
						if(strArr[i]==" "||strArr[i]==""||strArr[i]==null)continue;
						fileWord.add(strArr[i]);
					}
					Iterator<String> it=fileWord.iterator();
					while(it.hasNext()) {
						String key=it.next();
						if(idfMap.containsKey(key)) {
							idfMap.put(key, idfMap.get(key)+1);
						}
						else {
							idfMap.put(key,1.0);
						}
					}
				}
				//�ر��ļ�����
				br.close();
				reader.close();
			}
			//�쳣����
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			//��������еĴʵ��ĵ�Ƶ�ʺ󣬼���ÿ���ε����ĵ�Ƶ��
			for(Map.Entry<String,Double> entry:idfMap.entrySet()) {
				idfMap.put(entry.getKey(), Math.log10(1.0*TOTALDOC/entry.getValue()));
			}
		}
		return idfMap;
	}
}
