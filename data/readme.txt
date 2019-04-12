#该目录为索引文件路径
#索引文件配置见 /WEB-INF/web.xml ：
   <env-entry>
       <env-entry-name>solr/home</env-entry-name>
       #只需配置这个参数即可，参数值为索引文件所在的路径
       #window： 将data文件夹放置到WebServer所处磁盘的根目录下
       <env-entry-value>/data/solr/solr_home</env-entry-value>
       <env-entry-type>java.lang.String</env-entry-type>
    </env-entry>