from genkidamapy.client import Client

HOST = "localhost"
PORT = 22222

address = (HOST, PORT)

fibonacci_calc = """
def fib(n):
    if n < 2:
        return 1
    else:
        return fib(n-1) + fib(n-2)

print(fib(30))
"""

client = Client(address, connection_type="dummy")
request = client.exec(fibonacci_calc)
output_stream = request.get_output_stream()

client.stop()