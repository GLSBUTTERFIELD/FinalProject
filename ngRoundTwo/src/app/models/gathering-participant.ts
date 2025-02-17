import { GatheringParticipantId } from "./gathering-participant-id";

export class GatheringParticipant {
  gatheringParticipantId: GatheringParticipantId
  participantRating: number;
  participantNotes: string;
  hostRating: number;
  hostNotes: string;
  participantUsername: string;

  constructor(
    gatheringParticipantId: GatheringParticipantId,
    participantRating: number = 0,
    participantNotes: string = '',
    hostRating: number = 0,
    hostNotes: string = '',
    participantUsername: string = '',
  ){
    this.gatheringParticipantId = gatheringParticipantId;
    this.participantRating = participantRating;
    this.participantNotes = participantNotes;
    this.hostRating = hostRating;
    this.hostNotes = hostNotes;
    this.participantUsername = participantUsername;
  }
}
