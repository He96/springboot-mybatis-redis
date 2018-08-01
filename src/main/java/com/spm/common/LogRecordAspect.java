package com.spm.common;


/**
 * 方法的拦截
 * 用于日志记录  返回值统一封装
 * Created by MI on 2017/6/21.
 */
/*@Aspect   //定义一个切面
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component*/
public class LogRecordAspect {
  /*  @Autowired
    private LoginContext loginContext;
    Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);
    private ExecutorService fixedThreadPool;
    *//*@Autowired*//*
    private MongoDatabase mongoDatabase = MongoDbUtil.mongoDatabase;
    private static final String tbName ="logLog";

    public LogRecordAspect() {
        fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    }

    @Around("@annotation(requestMapping)")
    public Object doAround(ProceedingJoinPoint pjp, RequestMapping requestMapping) throws Throwable {
        Map<String, Object> logs = new HashMap<String, Object>();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        logs.put("_id", Long.valueOf(new SimpleDateFormat("yyMMddHHmmssSSS").format(date) + RandomString.createRandomOnlyString(4, "0123456789")));
        logs.put("CreatedTime", df.format(date));
        HttpServletRequest request = sra.getRequest();
        String path = request.getRequestURI();
        logs.put("Url", path);
        logs.put("Method", request.getMethod());
        logs.put("Token", loginContext.getValue());
        LoginInfo userinfo = loginContext.getInfo();
        logs.put("Username", userinfo != null ? userinfo.getFullName() : "anonymous");
        Object[] queryString = pjp.getArgs();
        try {
            logs.put("RequestParameter", JSON.toJSONString(queryString));

        } catch (Exception e) {
            logs.put("RequestError", "error");
        }
        try {
            long startTime = System.currentTimeMillis();//记录开始时间
            Object result = pjp.proceed();
            long endTime = System.currentTimeMillis();//记录结束时间
            logs.put("ResponseParameter", JSON.toJSONString(result));
            logs.put("elapsed", endTime - startTime);
            WriteLog(logs, request.getRequestURI());
            return result;
        } catch (Exception e) {
            logs.put("Error", "Exception");
            logs.put("ExceptiongMessage", e.toString());
            WriteLog(logs, request.getRequestURI());
            return new Result(-1, "服务器异常请稍后再试");
        }
    }

    private void WriteLog(Map<String, Object> logs, String url) {
        try {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ThreadId:"+Thread.currentThread().getId());
                    String data = JSON.toJSONString(logs);
                    Document doc = Document.parse(data);
                    if (url.indexOf("/error/") >= 0) {
                        MongoCollection<Document> collection = mongoDatabase.getCollection("error");
                        collection.insertOne(doc);
                    }
                    else {
                        MongoCollection<Document> collection = mongoDatabase.getCollection("loginLogs");
                        collection.insertOne(doc);
                    }

                }
            });
        } catch (Exception e) {

        }
    }

    protected void finalize() {
        if (fixedThreadPool != null) {
            fixedThreadPool.shutdown();
        }
    }*/
}
