import randomColor from "./Styling/RandomColor";
import colors from "./Styling/colors";

export default function LearnSetInfo(props) {
    /* This Component creates a information card from a learnSet */

    const learnSet = props.learnSet
    const classNames = props.classNames

    // create new date from unix
    const creationDate = new Date(learnSet.creationDate)
    const lastEdited = new Date(learnSet.lastEdited)

    const deleteLearnSet = (idLearnSet) => {
        fetch(`http://localhost:8080/api/learnset/` + idLearnSet, {method: 'DELETE'})
            .catch(e => console.log(e))
        window.location.assign("/")
    }
     

    return (
        <div className={classNames} onClick={() => window.location.href = `http://localhost:3000/${learnSet.id}`} style={{ backgroundColor: randomColor(), position: "relative" }}>
          <div className="grid-container">
            <div className="item-1">
              <h1 className="name">
                {learnSet.name} <br /> {learnSet.language1?.flag} ➜ {learnSet.language2?.flag}
              </h1>
              <button style={{ backgroundColor: "red", borderRadius: "50%", border: "2px solid black", position: "absolute", top: "1.25rem", right: "1.25rem" }} onClick={(e) => {
                  console.log(e);
                  deleteLearnSet(learnSet.id);
                e.stopPropagation()
              }}>X</button>
            </div>
            <img />
            <div className="item-2">
              <p><strong>Erstell datum:</strong> <br />{creationDate.toLocaleDateString()}</p>
              <p><strong>Zuletzt bearbeited:</strong> <br/>{lastEdited.toLocaleDateString()}</p>
            </div>
            <div className="item-3">
              <p><strong>Erste Sprache:</strong> <br />{learnSet.language1?.name}</p>
              <p><strong>Zweite Sprache:</strong> <br />{learnSet.language2?.name}</p>
            </div>
          </div>
        </div>
      );      
}