from genkidama.host import connect_to_device

HOST = "localhost"
PORT = 222222

address = (HOST, PORT)

fibonacci_calc = """
def fib(n):
    if n < 2:
        return 1
    else:
        return fib(n-1) + fib(n-2)

print(fib(30))
"""

device = connect_to_device(address)
request = device.exec(fibonacci_calc)
r_stdout = request.get_stdout()

print(r_stdout.read())

device.disconnect()