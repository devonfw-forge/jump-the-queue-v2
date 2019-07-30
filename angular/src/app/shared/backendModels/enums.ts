export enum Status {
    Waiting = 'WAITING',
    Attending = 'ATTENDING',
    Attended = 'ATTENDED',
    Skipped = 'SKIPPED',
    NotStarted = 'NOTSTARTED'
}

export enum Direction {
    ASC = 'ASC',
    DESC = 'DESC'
}

export enum SseTopic {
    QUEUE_STARTED = 'QUEUE_STARTED',
    CURRENT_CODE_CHANGED = 'CURRENT_CODE_CHANGED',
    CURRENT_CODE_CHANGED_NULL = 'CURRENT_CODE_CHANGED_NULL'
}
