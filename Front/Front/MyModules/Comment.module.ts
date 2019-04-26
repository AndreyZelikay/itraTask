export interface CommentModule {
    id: number;
    comment: string;
    userName: string;
    lastModifiedOn: Date;
    likes: {
        author: string
    }[];
}
