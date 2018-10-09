package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TF_IDF {
	//����ÿ���ĵ���ÿ���ʵ�tfidfֵ��������������
	public static HashMap<String,Map> getTFIDF(String dir){
		HashMap <String,Map>tfidfMaps=new HashMap <String,Map>();
		//��ȡ���д������ĵ�Ƶ��
		HashMap <String,Double>idfMap=IDF.getIDF(dir);
		//���������ļ����������ļ�
		File file=new File(dir);
		File[]files=file.listFiles();
		for(File f:files) {
			HashMap<String,Double> tfidfMap=new HashMap<String,Double>();
			String filename=f.getAbsolutePath();
			HashMap<String,Double> tfMap=TF.getTF(filename);
			for(Map.Entry<String, Double> entry:tfMap.entrySet()) {
				String key=entry.getKey();
				tfidfMap.put(key, tfMap.get(key)*idfMap.get(key));
			}
			tfidfMaps.put(filename, tfidfMap);
		}
		return tfidfMaps;
	}
}
