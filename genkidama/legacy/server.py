import genkidamapy.coms as coms
import genkidamapy.session as session

class Server(object):
    def __init__(self, port):
        self.connector = coms.Connector(port=port)
        self.session_handler = session.SessionManager(self.connector)

    def enter(self):
        self.connector.listen()
        while True:
            connection = self.connector.accept()
            self.session_handler.new_session(connection)
