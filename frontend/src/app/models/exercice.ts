export interface Exercice {
    id: number;
    title: string;
    description: string;
    content: string;
    image: string;
    levelDto: {
      id: number;
      name: string;
      categoryDto: {
        id: number;
        name: string;
      };
    };
    subjectDto: {
      id: number;
      name: string;
      levelDto: {
        id: number;
        name: string;
        categoryDto: {
          id: number;
          name: string;
        };
      };
    };
    solutionDto: {
      id: number;
      title: string;
      content: string;
    };
    userDto: {
      id: number;
      firstname: string;
      lastname: string;
      email: string;
      role: string;
    };
  }
  