package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TF_IDF {
	//计算每个文档、每个词的tfidf值，最后放入结果返回
	public static HashMap<String,Map> getTFIDF(String dir){
		HashMap <String,Map>tfidfMaps=new HashMap <String,Map>();
		//获取所有词逆向文档频率
		HashMap <String,Double>idfMap=IDF.getIDF(dir);
		//遍历给定文件夹下所有文件
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
