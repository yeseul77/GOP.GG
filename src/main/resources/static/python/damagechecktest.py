import joblib
import numpy as np

model_file="C:/Users/takealook/Desktop/machine_learning/BRONZE_Aatrox.pkl"
model_file_joblib=joblib.load(model_file)
champexp=12000
damage=[130000]
damage=np.array(damage)
damage=damage.reshape(-1,1)
result=model_file_joblib.predict(damage)
if(result==champexp):
    print("평균")
elif(result>champexp):
    print("평균이상")
elif(result<champexp):
    print("평균이하")