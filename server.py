from socket import *
from threading import Thread
import time

#储存对战玩家playerInfo[0] = [newData1, newData2]
playerInfo = {}
i = 0
waiting = []

def recv(newData, newAddr, i, flag):
    while True:
        try:
            recvData = newData.recv(1024)
            recvData = bytes.decode(recvData)+'\n'
            recvData = str.encode(recvData)
            if len(recvData) > 0:
                if flag == -1:
                    if waiting[i] == 0:
                        newData.send(b'waiting\n')
                        print("make",i,"waiting")
                    else:
                        playerInfo[i][1].send(recvData)
                        if recvData != b'\n':
                            print("telling",i,1,recvData)
                else:
                    playerInfo[i-1][0].send(recvData)
                    if recvData != b'\n':
                        print("telling",i-1,0,recvData)
            else:
                print(newAddr,"close!")
                break
        except:
            pass
    newData.close()

#服务器配置
tcpSocket = socket(AF_INET, SOCK_STREAM)
tcpSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
address = ('127.0.0.1', 2333)
tcpSocket.bind(address)
tcpSocket.listen(5)

flag = 1 #1为先 -1为后

try:
    while True:
        time.sleep(0.01)
        print('开启等待')
        newData, newAddr = tcpSocket.accept()
        print(newAddr,"connect!")
        if flag == 1:
            waiting.append(0)
            playerInfo[i]=[]
            playerInfo[i].append(newData)
            flag = -flag
        elif flag == -1:
            waiting[i]=1
            playerInfo[i].append(newData)
            i+=1
            flag = -flag
        p = Thread(target=recv, args=(newData, newAddr, i, flag))
        p.start()
finally:
    tcpSocket.close()



