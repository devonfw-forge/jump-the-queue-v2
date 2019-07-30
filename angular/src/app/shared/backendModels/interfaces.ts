import { Direction, Status} from './enums';

export class Queue {
    id: number;
    modificationCounter: number;
    minAttentionTime: number;
    started: boolean;
    createdDate: number;
}

export class AccessCode {
    id: number;
    modificationCounter: number;
    code: string;
    uuid: string;
    createdDate: number;
    startTime?: number;
    endTime?: number;
    status: Status;
    queueId: number;
}

export class Owner {
    id: number;
    modificationCounter: number;
    username: string;
    password: string;
    userType: boolean;
}

export class EstimatedTime {
    miliseconds: number;
    defaultTimeByUserInMs: number;
}

export class RemainingCodes {
    remainingCodes: number;
}

export class NextCodeCto {
    accessCode: AccessCode;
    remainingCodes: RemainingCodes;
}

export class CodeUuid {
    uuid: string;
}
