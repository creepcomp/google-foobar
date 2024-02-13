class Elevator:
    def __init__(self, elevator):
        major, minor, revision = map(int, elevator.strip().split('.'))
        self.version = elevator
        self.major = major
        self.minor = minor if minor else -1
        self.revision = revision if revision else -1
        
    def __lt__(self, other):
        return (self.major, self.minor, self.revision) < (other.major, other.minor, other.revision)

def solution(l):
    els = [Elevator(elevator) for elevator in l]
    els.sort()
    return [el.version for el in els]
