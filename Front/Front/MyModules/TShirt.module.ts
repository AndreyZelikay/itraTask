import {CommentModule} from './Comment.module';

export interface TShirt {
	id: number;
	description: string;
	url: string;
	name: string;
	tags: string[];
	comments: CommentModule[];
	theme: string;
	json: string;
	rating: number;
}
