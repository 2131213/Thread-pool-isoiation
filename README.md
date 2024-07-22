# Thread-pool-isoiation

本项目是一个简单的业务线程池隔离的demo

三个get请求
* http://localhost:8101/home/asyncA  A业务执行
* http://localhost:8101/home/asyncB  A业务执行
* http://localhost:8101/home/poolstatus  查看俩个线程池的占用情况
  
![image](https://github.com/user-attachments/assets/f64814a5-0299-4022-b27b-044741499555)
![image](https://github.com/user-attachments/assets/376e7ac6-26dc-459b-ba7d-ee584309eae8)

![image](https://github.com/user-attachments/assets/79660683-5904-4b54-b060-5d0e27a831d5)
