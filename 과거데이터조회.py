import pyupbit
import pandas as pd
import openpyxl

ticker = 'KRW-BTC'
start_date = '2020-06-15'
end_date = '2023-06-15'

df = pyupbit.get_ohlcv(ticker=ticker, interval='day', to=end_date, count=1095)
df = df[df.index >= start_date]  

df.to_excel('bitcoin_prices.xlsx')