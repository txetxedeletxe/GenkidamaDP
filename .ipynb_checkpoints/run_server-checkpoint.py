import socket
import os
import sys
from select import select
from io import StringIO

# new stuff
from genkidamapy.server import Server

STREAM_UNIT_SIZE = 1024

SESSION_TIMEOUT_SEC = 600

HOST = ""
PORT = 22222


if __name__ == "__main__":
    server = Server(port=PORT)
    server.enter()


if __name__ == "__main__":
    print("Listening on ip {ip} port {port}".format(ip=HOST, port=PORT))
    server_connector = ServerConnector(PORT)

    while True:
        connection = server_connector.accept()
        
        child_pid = os.fork()
        if child_pid != 0: # PARENT
            session_socket.close()
        else: # CHILD
            server_socket.close()

            print("Starting session with client: ", session_addr)
            while True: # while the session is up
                read_ready, _, _ = select([session_socket], [], [], SESSION_TIMEOUT_SEC)
                if not read_ready:
                    print("Session timeout of client ", session_addr)
                    break
                recv_bytes = session_socket.recv(STREAM_UNIT_SIZE)
                if recv_bytes is None:
                    break
                recv_str = bytes.decode(recv_bytes)

                sys.stdout = StringIO()
                try:
                    exec(recv_bytes)
                except Exception as e:
                    print(e)

                send_str = sys.stdout.getvalue()
                send_bytes = send_str.encode()
                session_socket.sendall(send_bytes)

            print("Session with client ", session_addr, " over")
            session_socket.close()
            break


    
