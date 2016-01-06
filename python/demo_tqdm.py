# demo for use tqdm
# progress meter
# https://github.com/noamraph/tqdm
import time
from tqdm import tqdm

for i in tqdm(range(1000)):
    time.sleep(.01)
