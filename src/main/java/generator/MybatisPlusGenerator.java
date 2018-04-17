package generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis-plus代码生成器<br>
 *
 * @author stylefeng
 * @Date 2017/5/21 12:38
 */
public class MybatisPlusGenerator {
	/**
	 * 项目路径
	 */
	private static String realPath = "G:\\ydjava\\managementsystem";
	/**
	 * 包路径
	 */
	private static String packagePath = "com.yatoooon.managementsystem";
	/**
	 * 是否覆盖
	 */
	private static boolean fileOverride = true;
	/**
	 * 要生成的表名称
	 */
	private static String[] tableName = new String[]{"test"};

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(realPath + "\\src\\main\\java");//这里写你自己的java目录
		gc.setFileOverride(fileOverride);//是否覆盖
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("yatoooon");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		dsc.setUrl("jdbc:mysql://localhost:3306/management_system?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		//strategy.setTablePrefix(new String[]{"t_"});//此处可以修改为您的表前缀比如t_xxx表
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(tableName);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(null);
		pc.setEntity(packagePath + ".model");
		pc.setMapper(packagePath + ".dao");
		pc.setXml(packagePath + ".dao.mapper");
		pc.setService(packagePath + ".service");
		pc.setServiceImpl(packagePath + ".service.impl");
		pc.setController(packagePath + ".controller");
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};

		/*// 自定义 xxList.jsp 生成
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		focList.add(new FileOutConfig("/templates/index.vue.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return realPath + "\\vue_elementui\\src\\views\\" + tableInfo.getEntityName().toLowerCase() + "table" + "\\index.vue";
			}
		});
		cfg.setFileOutConfigList(focList);*/

		mpg.setCfg(cfg);

		// 执行生成
		mpg.execute();

		// 打印注入设置
		System.err.println(mpg.getCfg().getMap().get("abc"));
	}

}